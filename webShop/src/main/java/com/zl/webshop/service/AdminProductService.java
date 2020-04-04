/**
 * <p>
 * Title: AdminProductService.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月30日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.service;

import java.util.List;
import com.zl.webshop.dto.ProductExecution;
import com.zl.webshop.entity.Product;
import com.zl.webshop.entity.ProductImage;

/**
 * <p>
 * Title: AdminProductService
 * </p>
 * <p>
 * Description: 管理员商品业务接口
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月30日
 *         </p>
 */
public interface AdminProductService {

  /**
   * 
   * <p>
   * Title: getProducts
   * </p>
   * <p>
   * Description:
   * </p>
   * 
   * @param searchText 搜索关键词 可填可不填
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 商品列表
   */
  List<ProductExecution> getProducts(String searchText, int offset, int limit);

  /**
   * 
   * <p>
   * Title: updateProduct
   * </p>
   * <p>
   * Description: 更新一个商品
   * </p>
   * 
   * @param product 商品对象
   * @return 更新成功后的产品数据
   */
  ProductExecution updateProduct(Product product, List<ProductImage> otherImages);

  /**
   * 
   * <p>
   * Title: deleteProduct
   * </p>
   * <p>
   * Description: 删除一个商品
   * </p>
   * 
   * @param product 商品
   * @return 返回最新商品总数
   */
  ProductExecution deleteProduct(Product product);

  /**
   * 
   * <p>
   * Title: addProduct
   * </p>
   * <p>
   * Description: 新增一个商品
   * </p>
   * 
   * @param product 商品
   * @return 返回最新商品总数
   */
  ProductExecution addProduct(Product product);

  /**
   * 
   * <p>
   * Title: addOtherImages
   * </p>
   * <p>
   * Description: 添加商品的其他图片
   * </p>
   * 
   * @param product 商品
   * @param otherImages 商品的其他图片
   * @return 添加图片后的完整商品对象
   */
  ProductExecution addOtherImages(Product product, List<ProductImage> otherImages);

  /**
   * 
   * <p>
   * Title: deleteOtherImages
   * </p>
   * <p>
   * Description: 删除商品的其他图片
   * </p>
   * 
   * @param product 商品
   * @return 删除其他图片后的商品对象
   */
  ProductExecution deleteOtherImages(Product product);

  /**
   * 
   * <p>
   * Title: updateOtherImages
   * </p>
   * <p>
   * Description: 更新商品的其他图片
   * </p>
   * 
   * @param product 商品
   * @param otherImages 商品的其他图片
   * @return 返回更新后的完整的商品对象
   */
  ProductExecution updateOtherImages(Product product, List<ProductImage> otherImages);
}
