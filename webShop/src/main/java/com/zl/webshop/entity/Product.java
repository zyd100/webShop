/**
 * <p>
 * Title: Product.java
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
 * Title: Product
 * </p>
 * <p>
 * Description: 产品表实体类
 * </p>
 * @author zyd @date 2020年3月11日
 */
public class Product {
  /**
   * 序号
   */
  private long id;
  /**
   * 产品名
   */
  private String productName;
  /**
   * 产品说明
   */
  private String explain;
  /**
   * 类别序号
   */
  private int categoryId;
  /**
   * 店内价格
   */
  private float shopPrice;
  /**
   * 市场价格
   */
  private float price;
  /**
   * 数量
   */
  private int quantity;
  /**
   * 图片
   */
  private String image;
  /**
   * 创建时间
   */
  private LocalDateTime createTime;
  /**
   * 更新时间
   */
  private LocalDateTime updateTime;
  /**
   * 创建者名
   */
  private String createUserName;
  /**
   * 更新者名
   */
  private String updateUserName;

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
   * @return the explain 产品说明
   */
  public String getExplain() {
    return explain;
  }

  /**
   * @param explain the explain to set
   */
  public void setExplain(String explain) {
    this.explain = explain;
  }

  /**
   * @return the categoryId 类别序号
   */
  public int getCategoryId() {
    return categoryId;
  }

  /**
   * @param categoryId the categoryId to set
   */
  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  /**
   * @return the shopPrice 店内价格
   */
  public float getShopPrice() {
    return shopPrice;
  }

  /**
   * @param shopPrice the shopPrice to set
   */
  public void setShopPrice(float shopPrice) {
    this.shopPrice = shopPrice;
  }

  /**
   * @return the price 价格
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

  /**
   * @return the image 图片
   */
  public String getImage() {
    return image;
  }

  /**
   * @param image the image to set
   */
  public void setImage(String image) {
    this.image = image;
  }

  /**
   * @return the createTime 创建商品时间
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

  /**
   * @return the updateTime 更新商品时间
   */
  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  /**
   * @param updateTime the updateTime to set
   */
  public void setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
  }

  /**
   * @return the createUserName 创建者名
   */
  public String getCreateUserName() {
    return createUserName;
  }

  /**
   * @param createUserName the createUserName to set
   */
  public void setCreateUserName(String createUserName) {
    this.createUserName = createUserName;
  }

  /**
   * @return the updateUserName 更新者名
   */
  public String getUpdateUserName() {
    return updateUserName;
  }

  /**
   * @param updateUserName the updateUserName to set
   */
  public void setUpdateUserId(String updateUserName) {
    this.updateUserName = updateUserName;
  }

 
  @Override
  public String toString() {
    return "Product [id=" + id + ", productName=" + productName + ", explain=" + explain
        + ", categoryId=" + categoryId + ", shopPrice=" + shopPrice + ", price=" + price
        + ", quantity=" + quantity + ", image=" + image + ", createTime=" + createTime
        + ", updateTime=" + updateTime + ", createUserName=" + createUserName + ", updateUserName="
        + updateUserName + "]";
  }

}
