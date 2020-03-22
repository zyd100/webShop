/**
 * <p>
 * Title: UserRolesDao.java
 * </p>
 * <p>
 * Description: 用户权限表Dao
 * </p>
 * 
 * @author zyd @date 2020年3月12日 @version 1.0
 */
package com.zl.webshop.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.zl.webshop.entity.UserRoles;

/**
 * <p>
 * Title: UserRolesDao
 * </p>
 * <p>
 * Description: 用户权限表Dao接口
 * </p>
 * 
 * @author zyd 
 * <p>创建日期：2020年3月12日</p>
 */
public interface UserRolesDao {
  /**
   * 
   * <p>
   * Title: count
   * </p>
   * <p>
   * Description: 获取用户权限表总数
   * </p>
   * @return 用户权限总数
   */
  int count();
  /**
   * 
   * <p>
   * Title: queryAll
   * </p>
   * <p>
   * Description: 查询所有用户权限信息
   * </p>
   * 
   * @param offset 查询起始位置
   * @param limit 查询条目数
   * @return 用户权限清单
   */
  List<UserRoles> queryAll(@Param("offset") int offset, @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: queryAllByRole
   * </p>
   * <p>
   * Description: 根据用户权限查询用户权限信息
   * </p>
   * 
   * @param role 权限值
   * @param offset 查询起始位置
   * @param limit 查询条目数
   * @return 用户权限清单
   */
  List<UserRoles> queryAllByRole(@Param("role") String role, @Param("offset") int offset,
      @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: queryByUserName
   * </p>
   * <p>
   * Description: 根据用户名查询用户权限信息
   * </p>
   * 
   * @param userName 用户名
   * @return 用户权限信息
   */
  UserRoles queryByUserName(@Param("userName") String userName);

  /**
   * 
   * <p>
   * Title: addRole
   * </p>
   * <p>
   * Description: 添加用户权限信息
   * </p>
   * 
   * @param userName 用户名
   * @param role 权限值
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int addRole(@Param("userName") String userName, @Param("role") String role);

  /**
   * 
   * <p>
   * Title: updateRole
   * </p>
   * <p>
   * Description: 更新用户权限信息
   * </p>
   * 
   * @param userName 用户名
   * @param role 权限值
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int updateRole(@Param("userName") String userName, @Param("role") String role);

  /**
   * 
   * <p>
   * Title: deleteRole
   * </p>
   * <p>
   * Description: 删除用户权限信息
   * </p>
   * 
   * @param userName 用户名
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteRole(@Param("userName") String userName);
}
