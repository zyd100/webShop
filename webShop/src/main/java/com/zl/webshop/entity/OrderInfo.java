/**
 * <p>
 * Title: OrderInfo.java
 * </p>
 * <p>
 * Description:
 * </p>
 * @author zyd @date 2020年3月11日 @version 1.0
 */
package com.zl.webshop.entity;

import java.time.LocalDateTime;

/**
 * <p>
 * Title: OrderInfo
 * </p>
 * <p>
 * Description: 订单信息表实体类
 * </p>
 * @author zyd @date 2020年3月11日
 */
public class OrderInfo {
  /**
   * 序号
   */
  private long id;
  /**
   * 订单编号
   */
  private String orderNum;
  /**
   * 用户名
   */
  private String userName;
  /**
   * 合计金额
   */
  private float price;
  /**
   * 订单状态（下单0/配送1/寄到2）
   */
  private int status;
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
   * 订单备注
   */
  private String message;
  /**
   * 订单创建时间
   */
  private LocalDateTime createTime;

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
   * @return the orderNum 订单编号
   */
  public String getOrderNum() {
    return orderNum;
  }

  /**
   * @param orderNum the orderNum to set
   */
  public void setOrderNum(String orderNum) {
    this.orderNum = orderNum;
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
   * @return the price 合计金额
   */
  public float getPrice() {
    return price;
  }

  /**
   * @param price the price to set
   */
  public void setPrice(float price) {
    this.price = price;
  }

  /**
   * @return the status 订单状态
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
   * @return the message 订单备注
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param message the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * @return the createDateTime 创建时间
   */
  public LocalDateTime getCreateTime() {
    return createTime;
  }

  /**
   * @param createTime the createTime to set
   */
  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }


  @Override
  public String toString() {
    return "OrderInfo [id=" + id + ", orderNum=" + orderNum + ", userName=" + userName + ", price="
        + price + ", status=" + status + ", contactName=" + contactName + ", contactMobile="
        + contactMobile + ", contactAddress=" + contactAddress + ", message=" + message
        + ", createTime=" + createTime + "]";
  }

}
