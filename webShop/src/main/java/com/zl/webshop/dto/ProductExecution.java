/**
 * <p>
 * Title: ProductExecution.java
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
import com.zl.webshop.entity.Product;
import com.zl.webshop.entity.ProductImage;

/**
 * <p>
 * Title: ProductExecution
 * </p>
 * <p>
 * Description: 产品值传递对象，保存了产品类别，产品序号以及产品对象
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月15日
 *         </p>
 */
public class ProductExecution {
  /**
   * 产品类别名
   */
  private String categoryName;
  /**
   * 产品类别序号
   */
  private long categoryId;
  /**
   * 产品表总数
   */
  private long productMaxCount;
  /**
   * 产品列表
   */
  private List<Product> productList;
  /**
   * 单个产品
   */
  private Product product;
  /**
   * 单个产品的其他图片
   */
  private List<ProductImage>productImages;

  /**
   * 
   * <p>
   * Title:
   * </p>
   * <p>
   * Description: 有分类的构造器
   * </p>
   * 
   * @param categoryId 分类序号
   * @param categoryName 分类名
   * @param productList 产品列表
   */
  public ProductExecution(long categoryId, String categoryName, List<Product> productList) {
    setCategoryId(categoryId);
    setCategoryName(categoryName);
    setProductList(productList);
  }
  /**
   * 
  　 * <p>Title: 默认构造器</p> 
  　 * <p>Description: 默认构造器</p>
   */
  public ProductExecution() {};
  /**
   * 
   * <p>
   * Title:
   * </p>
   * <p>
   * Description: 无分类的构造器
   * </p>
   * 
   * @param productList 产品列表
   */
  public ProductExecution(List<Product> productList) {
    setProductList(productList);
  }

  /**
   * 
   * <p>
   * Title:
   * </p>
   * <p>
   * Description: 单个产品的构造器
   * </p>
   * 
   * @param categoryId 分类序号
   * @param categoryName 分类名
   * @param product 产品
   */
  public ProductExecution(long categoryId, String categoryName, Product product) {
    setCategoryId(categoryId);
    setCategoryName(categoryName);
    setProduct(product);
  }

  /**
   * @return the categoryId 产品类别序号
   */
  public long getCategoryId() {
    return categoryId;
  }

  /**
   * @param categoryId the categoryId to set
   */
  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

  /**
   * @return the productList 产品列表
   */
  public List<Product> getProductList() {
    return productList;
  }

  /**
   * @param productList the productList to set
   */
  public void setProductList(List<Product> productList) {
    this.productList = productList;
  }

  /**
   * @return the categoryName 产品类别名
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
   * @return the productMaxCount
   */
  public long getProductMaxCount() {
    return productMaxCount;
  }

  /**
   * @param productMaxCount the productMaxCount to set
   */
  public void setProductMaxCount(long productMaxCount) {
    this.productMaxCount = productMaxCount;
  }

  /**
   * @return the product
   */
  public Product getProduct() {
    return product;
  }

  /**
   * @param product the product to set
   */
  public void setProduct(Product product) {
    this.product = product;
  }

  public List<ProductImage> getProductImages() {
    return productImages;
  }
  public void setProductImages(List<ProductImage> productImages) {
    this.productImages = productImages;
  }
  @Override
  public String toString() {
    return "ProductExecution [categoryName=" + categoryName + ", categoryId=" + categoryId
        + ", productMaxCount=" + productMaxCount + ", productList=" + productList + ", product="
        + product + ", productImages=" + productImages + "]";
  }

}
