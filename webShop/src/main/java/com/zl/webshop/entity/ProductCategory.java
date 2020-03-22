/**
 * <p>
 * Title: ProductCategory.java
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
 * Title: ProductCategory
 * </p>
 * <p>
 * Description: 产品类别表实体类
 * </p>
 * @author zyd @date 2020年3月11日
 */
public class ProductCategory {
  /**
   * 序号
   */
  private long id;
  /**
   * 类别名
   */
  private String categoryName;
  /**
   * 类别排序
   */
  private int sortOrder;
  /**
   * 类别说明
   */
  private String description;
  /**
   * 图片
   */
  private String image;
  /**
   * 更新时间
   */
  private LocalDateTime upDateTime;

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
   * @return the categoryName 类别名
   */
  public String getCategoryName() {
    return categoryName;
  }

  /**
   * @param categoryName the categoryName to set
   */
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  /**
   * @return the sortOrder 类别排序
   */
  public int getSortOrder() {
    return sortOrder;
  }

  /**
   * @param sortOrder the sortOrder to set
   */
  public void setSortOrder(int sortOrder) {
    this.sortOrder = sortOrder;
  }

  /**
   * @return the description 类别说明
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
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
   * @return the upDateTime 更新时间
   */
  public LocalDateTime getUpDateTime() {
    return upDateTime;
  }

  /**
   * @param upDateTime the upDateTime to set
   */
  public void setUpDateTime(LocalDateTime upDateTime) {
    this.upDateTime = upDateTime;
  }


  @Override
  public String toString() {
    return "ProductCategory [id=" + id + ", categoryName=" + categoryName + ", sortOrder="
        + sortOrder + ", description=" + description + ", image=" + image + ", upDateTime="
        + upDateTime + "]";
  }

}
