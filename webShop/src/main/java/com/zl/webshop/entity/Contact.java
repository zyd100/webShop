/**
 * <p>
 * Title: Contact.java
 * </p>
 * <p>
 * Description:
 * </p>
 * @author zyd @date 2020年3月11日 @version 1.0
 */
package com.zl.webshop.entity;

/**
 * <p>
 * Title: Contact
 * </p>
 * <p>
 * Description: 联系信息表实体
 * </p>
 * @author zyd @date 2020年3月11日
 */
public class Contact {
  /**
   * 序号
   */
  private long id;
  /**
   * 用户名
   */
  private String userName;
  /**
   * 联系人
   */
  private String contactName;
  /**
   * 联系电话
   */
  private String contactMobile;
  /**
   * 联系地址
   */
  private String contactAddress;
  /**
   * 联系信息状态 0默认地址/1非默认地址
   */
  private int status;

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
   * @return the contactName 联系人
   */
  public String getContactName() {
    return contactName;
  }

  /**
   * @param contactName the contactName to set
   */
  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  /**
   * @return the contactMobile 联系电话
   */
  public String getContactMobile() {
    return contactMobile;
  }

  /**
   * @param contactMobile the contactMobile to set
   */
  public void setContactMobile(String contactMobile) {
    this.contactMobile = contactMobile;
  }

  /**
   * @return the contactAddress 联系地址
   */
  public String getContactAddress() {
    return contactAddress;
  }

  /**
   * @param contactAddress the contactAddress to set
   */
  public void setContactAddress(String contactAddress) {
    this.contactAddress = contactAddress;
  }

  /**
   * @return the status 联系信息设置状态0默认地址/1非默认地址
   */
  public int getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(int status) {
    this.status = status;
  }


  @Override
  public String toString() {
    return "Contact [id=" + id + ", userName=" + userName + ", contactName=" + contactName
        + ", contactMobile=" + contactMobile + ", contactAddress=" + contactAddress + ", status="
        + status + "]";
  }

}
