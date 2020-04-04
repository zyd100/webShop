/**
 * <p>
 * Title: ProductImage.java
 * </p>
 * <p>
 * Description:
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年4月3日
 * </p>
 * @version 1.0
 */
package com.zl.webshop.entity;

/**
 * <p>
 * Title: ProductImage
 * </p>
 * <p>
 * Description: 产品图片表实体类
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年4月3日
 * </p>
 */
public class ProductImage {
  /**
   * 序号
   */
  private long id;
  /**
   * 产品序号
   */
  private long productId;
  /**
   * 图片
   */
  private String image;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  @Override
  public String toString() {
    return "ProductImage [id=" + id + ", productId=" + productId + ", image=" + image + "]";
  }

}
