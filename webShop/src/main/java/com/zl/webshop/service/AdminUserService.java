/**
 * <p>
 * Title: AdminUserService.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月30日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.service;

import java.util.List;
import com.zl.webshop.dto.UserExecution;
import com.zl.webshop.entity.User;

/**
 * <p>
 * Title: AdminUserService
 * </p>
 * <p>
 * Description: 管理用户业务接口
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月30日
 *         </p>
 */
public interface AdminUserService {

  /**
   * 
   * <p>
   * Title: getUsers
   * </p>
   * <p>
   * Description: 获取用户列表
   * </p>
   * 
   * @param searchText 搜索关键词 选填 @param offset 查询起始位置 @param limit 查询条数 @return 用户列表
   */
  List<UserExecution> getUsers(String searchText, int offset, int limit);

  /**
   * 
   * <p>
   * Title: deleteUser
   * </p>
   * <p>
   * Description: 删除用户
   * </p>
   * 
   * @param user 用户 @return 最新用户表总数
   */
  int deleteUser(User user);

  /**
   * 
   * <p>
   * Title: updateUser
   * </p>
   * <p>
   * Description: 更新一个用户
   * </p>
   * 
   * @param user 用户对象
   * @return 更新后的用户信息
   */
  UserExecution updateUser(User user);
}
