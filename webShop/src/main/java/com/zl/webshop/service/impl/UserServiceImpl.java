/**
 * <p>
 * Title: UserServiceImpl.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月18日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.service.impl;

import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zl.webshop.dao.ContactDao;
import com.zl.webshop.dao.OrderInfoDao;
import com.zl.webshop.dao.OrderItemDao;
import com.zl.webshop.dao.UserDao;
import com.zl.webshop.dao.UserRolesDao;
import com.zl.webshop.dto.UserExecution;
import com.zl.webshop.entity.User;
import com.zl.webshop.entity.UserRoles;
import com.zl.webshop.enums.UserRolesEnum;
import com.zl.webshop.exception.DeleteException;
import com.zl.webshop.exception.LoginException;
import com.zl.webshop.exception.NoUserException;
import com.zl.webshop.exception.RegisterException;
import com.zl.webshop.exception.RepeatRegisterException;
import com.zl.webshop.exception.UpdateException;
import com.zl.webshop.exception.WrongUserNamePwdException;
import com.zl.webshop.service.UserService;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;

/**
 * <p>
 * Title: UserServiceImpl
 * </p>
 * <p>
 * Description: UserService 接口实例
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月18日
 *         </p>
 */
@Service
public class UserServiceImpl implements UserService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private UserDao userdao;
  @Autowired
  private UserRolesDao userRolesDao;
  @Autowired
  private ContactDao contactDao;
  @Autowired
  private OrderItemDao orderItemDao;
  @Autowired
  private OrderInfoDao orderInfoDao;

  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public UserExecution register(User applicant) throws RegisterException {
    UserExecution userExecution = null;
    try {
      User user = userdao.queryByUserName(applicant.getUserName());
      if (null != user) {
        // 已存在该用户
        throw new RepeatRegisterException("repeat register");
      } else {
        // 注册
        if (StrUtil.isNotEmpty(applicant.getPassword())
            && StrUtil.isNotEmpty(applicant.getUserName())) {
          // 用户名密码都存在
          // 密码加密
          String pwdMd5 = DigestUtil.md5Hex(applicant.getPassword());
          applicant.setPassword(pwdMd5);
          // 设置普通权限
          UserRolesEnum rolesEnum = UserRolesEnum.CUSTOM;
          int count = userdao.addUser(applicant);
          if (count > 0) {
            // 注册成功，添加权限
            // 添加权限
            count = userRolesDao.addRole(applicant.getUserName(), rolesEnum.getStateInfo());
            if (count > 0) {
              // 添加权限成功
              // 装填数据返回结果
              // 过滤非必要数据
              applicant.setPassword(null);
              userExecution = new UserExecution(applicant, rolesEnum);
            } else {
              // 添加权限失败
              throw new RegisterException("add role error");
            }
          } else {
            // 添加用户失败
            throw new RegisterException("add user error");
          }
        }
      }
    } catch (RepeatRegisterException e) {
      throw e;
    } catch (RegisterException e) {
      throw e;
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new RegisterException("register inner error:" + e.getMessage());
    }
    return userExecution;
  }

  @Override
  public boolean login(User user) throws LoginException {
    boolean index = false;
    try {
      if (StrUtil.isEmpty(user.getUserName()) || StrUtil.isEmpty(user.getPassword())) {
        // 密码或用户名为空
        index = false;
        throw new WrongUserNamePwdException("empty userName or password");
      }
      User checkUser = userdao.queryByUserName(user.getUserName());
      if (null == checkUser) {
        // 查无此用户
        index = false;
        throw new NoUserException("no user");
      } else {
        // 验证
        if (DigestUtil.md5Hex(user.getPassword()).equals(checkUser.getPassword()) == true) {
          // 密码正确，登录成功
          index = true;
        } else {
          // 密码错误
          index = false;
          throw new WrongUserNamePwdException("wrong userName or password");
        }
      }
    } catch (NoUserException e) {
      throw e;
    } catch (WrongUserNamePwdException e) {
      throw e;
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new LoginException("login inner error:" + e.getMessage());
    }

    return index;
  }

  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public int deleteAccount(User user) {
    // 检测是否删除成功
    int index = 0;
    // 删除累计行数
    int flag = 0;
    try {
      User checkUser = userdao.queryByUserName(user.getUserName());
      if (null == checkUser) {
        // 查无此人
        throw new NoUserException("no user");
      }
      if (StrUtil.equals(checkUser.getPassword(), DigestUtil.md5Hex(user.getPassword())) == false) {
        // 密码错误
        throw new WrongUserNamePwdException("wrong userName or password");
      }
      // 先删除子表数据后再删除用户表(主表)数据 主表必须在子表全部删除完毕后才能删除
      // 删除权限
      index = userRolesDao.deleteRole(user.getUserName());
    
      flag += index;
      // 删除联系信息
      index = contactDao.deleteContactByUserName(user.getUserName());
      
       
      
      flag += index;
      // 删除订单条目
      index = orderItemDao.deleteOrderItemByUserName(user.getUserName());
      
      flag += index;
      // 删除订单信息
      index = orderInfoDao.deleteOrderInfoByUserName(user.getUserName());
     
      flag += index;
      // 删除用户
      index = userdao.deleteUser(user);
     
      flag += index;
    } catch (NoUserException e) {
      throw e;
    } catch (WrongUserNamePwdException e) {
      throw e;
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new DeleteException("deleteAccount inner error:" + e.getMessage());
    }
    return flag;
  }

  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public int updateUserInfo(User user) throws UpdateException {
    int index = 0;
    try {
      // 防止覆盖原密码
      User preUser = userdao.queryByUserName(user.getUserName());
      user.setPassword(preUser.getPassword());
      index = userdao.updateUser(user);
      if (index < 1) {
        // 更新失败
        throw new UpdateException("update error");
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new UpdateException("update user info inner error:" + e.getMessage());
    }
    return index;
  }

  @Override
  public UserExecution getBasicInfo(String userName) {
    UserExecution userExecution = null;
    try {
      User user = userdao.queryByUserName(userName);
      UserRoles userRoles = userRolesDao.queryByUserName(userName);
      if (ObjectUtil.isEmpty(user) || ObjectUtil.isEmpty(userRoles)) {
        throw new NoUserException("no user");
      }
      // 过滤私密信息
      user.setPassword(null);
      // 装填信息
      UserRolesEnum userRolesEnum = Stream.of(UserRolesEnum.values())
          .filter(x -> x.getStateInfo().equals(userRoles.getRole())).findAny().get();
      userExecution = new UserExecution(user, userRolesEnum);
    } catch (NoUserException e) {
      throw e;
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw e;
    }
    return userExecution;
  }

  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public int updatePassword(User user) {
    int index = 0;
    try {
      User preUser = userdao.queryByUserName(user.getUserName());
      if (preUser == null) {
        throw new NoUserException("no user");
      }
      preUser.setPassword(DigestUtil.md5Hex(user.getPassword()));
      index += userdao.updateUser(preUser);
    } catch (NoUserException e) {
      throw e;
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new UpdateException("update password inner error:" + e.getMessage());
    }
    return index;
  }

  @Override
  public boolean checkPassword(String userName, String password) {
    boolean flag=false;
    try {
      User user=userdao.queryByUserName(userName);
      if(user==null) {
        throw new NoUserException("no user");
      }
      //加密密码
      password=DigestUtil.md5Hex(password);
      if(ObjectUtil.equal(password, user.getPassword())) {
        flag=true;
      }else {
        flag=false;
      }
    }catch (NoUserException e) {
      throw e;
    } 
    catch (Exception e) {
     logger.error(e.getMessage());
     throw e;
    }
    return flag;
  }

}
