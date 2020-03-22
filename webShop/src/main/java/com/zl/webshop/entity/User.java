package com.zl.webshop.entity;

import java.time.LocalDateTime;

/**
 * <p>
 * Title: User
 * </p>
 * <p>
 * Description:User表实体类
 * </p>
 * @author zyd @date 2020年3月11日
 */
public class User {
  /**
   * id 序号
   */
  private long id;
  /**
   * userName 用户名
   */
  private String userName;
  /**
   * password 密码
   */
  private String password;
  /**
   * nickName 昵称
   */
  private String nickName;
  /**
   * email 邮箱
   */
  private String email;
  /**
   * registerTime 注册时间
   */
  private LocalDateTime registerTime;
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
  /**
  * @return the password 密码
  */
  public String getPassword() {
    return password;
  }
  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }
  /**
  * @return the nickName 昵称
  */
  public String getNickName() {
    return nickName;
  }
  /**
   * @param nickName the nickName to set
   */
  public void setNickName(String nickName) {
    this.nickName = nickName;
  }
  /**
  * @return the email 邮箱
  */
  public String getEmail() {
    return email;
  }
  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }
  /**
  * @return the registerTime 注册时间
  */
  public LocalDateTime getRegisterTime() {
    return registerTime;
  }
  /**
   * @param registerTime the registerTime to set
   */
  public void setRegisterTime(LocalDateTime registerTime) {
    this.registerTime = registerTime;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", nickName="
        + nickName + ", email=" + email + ", registerTime=" + registerTime + "]";
  }

}
