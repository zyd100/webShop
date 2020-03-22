/**
 * <p>
 * Title: UserRoles.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd @date 2020年3月11日 @version 1.0
 */
package com.zl.webshop.entity;

/**
 * <p>
 * Title: UserRoles
 * </p>
 * <p>
 * Description: 用户权限表实体类
 * </p>
 * 
 * @author zyd @date 2020年3月11日
 */
public class UserRoles {
  /**
   * id 序号
   */
  private long id;
  /**
   * role 权限
   */
  private String role;
  /**
   * userName 用户名
   */
  private String userName;


  /**
   * @return the id 序号
   */
  public long getId() {
    return id;
  }


  /**
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
  }


  /**
   * @return the role 权限名
   */
  public String getRole() {
    return role;
  }


  /**
   * @param role the role to set
   */
  public void setRole(String role) {
    this.role = role;
  }


  /**
   * @return the userName 用户名
   */
  public String getUserName() {
    return userName;
  }


  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }


  @Override
  public String toString() {
    return "UserRoles [id=" + id + ", role=" + role + ", userName=" + userName + "]";
  }

}
