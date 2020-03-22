/**
 * <p>
 * Title: OrderItem.java
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
 * Title: OrderItem
 * </p>
 * <p>
 * Description: 订单条目表实体类
 * </p>
 * 
 * @author zyd @date 2020年3月11日
 */
public class OrderItem {
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
   * 产品名
   */
  private String productName;
  /**
   * 产品序号
   */
  private long productId;
  /**
   * 单价
   */
  private float price;
  /**
   * 数量
   */
  private int quantity;

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
   * @return the productName 产品名
   */
  public String getProductName() {
    return productName;
  }

  /**
   * @param productName the productName to set
   */
  public void setProductName(String productName) {
    this.productName = productName;
  }

  /**
   * @return the productId 产品序号
   */
  public long getProductId() {
    return productId;
  }

  /**
   * @param productId the productId to set
   */
  public void setProductId(long productId) {
    this.productId = productId;
  }

  /**
   * @return the price 单价
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
   * @return the quantity 数量
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * @param quantity the quantity to set
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

 
  @Override
  public String toString() {
    return "OrderItem [id=" + id + ", orderNum=" + orderNum + ", userName=" + userName
        + ", productName=" + productName + ", productId=" + productId + ", price=" + price
        + ", quantity=" + quantity + "]";
  }

}
