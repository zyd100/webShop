/**
 * <p>
 * Title: UserExecution.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月15日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.dto;

import java.util.List;
import com.zl.webshop.entity.User;
import com.zl.webshop.enums.UserRolesEnum;

/**
 * <p>
 * Title: UserExecution
 * </p>
 * <p>
 * Description: 用户表DTO,保存用户权限以及用户信息
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月15日
 *         </p>
 */
public class UserExecution {

  private User user;
  private String roleInfo;
  private int roleState;
  private List<User> users;
  private int userMaxCount;

  /**
   * 
   * <p>
   * Title: 单个用户的构造器
   * </p>
   * <p>
   * Description: 单个用户的构造器
   * </p>
   * 
   * @param user 用户
   * @param userRolesEnum 用户权限
   */
  public UserExecution(User user, UserRolesEnum userRolesEnum) {
    setUser(user);
    setRoleInfo(userRolesEnum.getStateInfo());
    setRoleState(userRolesEnum.getState());
  }

  /**
   * 
   * <p>
   * Title:
   * </p>
   * <p>
   * Description: 分类用户的构造器
   * </p>
   * 
   * @param users 用户列表
   * @param userRolesEnum 用户权限
   */
  public UserExecution(List<User> users, UserRolesEnum userRolesEnum) {
    setUsers(users);
    setRoleInfo(userRolesEnum.getStateInfo());
    setRoleState(userRolesEnum.getState());
  }

  /**
   * 
   * <p>
   * Title:
   * </p>
   * <p>
   * Description: 不分类多个用户的构造器
   * </p>
   * 
   * @param users 用户列表
   */
  public UserExecution(List<User> users) {
    setUsers(users);
  }

  /**
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /**
   * @param user the user to set
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * @return the roleInfo
   */
  public String getRoleInfo() {
    return roleInfo;
  }

  /**
   * @param roleInfo the roleInfo to set
   */
  public void setRoleInfo(String roleInfo) {
    this.roleInfo = roleInfo;
  }

  /**
   * @return the roleState
   */
  public int getRoleState() {
    return roleState;
  }

  /**
   * @param roleState the roleState to set
   */
  public void setRoleState(int roleState) {
    this.roleState = roleState;
  }

  /**
   * @return the users
   */
  public List<User> getUsers() {
    return users;
  }

  /**
   * @param users the users to set
   */
  public void setUsers(List<User> users) {
    this.users = users;
  }

  /**
   * @return the userMaxCount
   */
  public int getUserMaxCount() {
    return userMaxCount;
  }

  /**
   * @param userMaxCount the userMaxCount to set
   */
  public void setUserMaxCount(int userMaxCount) {
    this.userMaxCount = userMaxCount;
  }

  @Override
  public String toString() {
    return "UserExecution [user=" + user + ", roleInfo=" + roleInfo + ", roleState=" + roleState
        + ", users=" + users + ", userMaxCount=" + userMaxCount + "]";
  }

}
