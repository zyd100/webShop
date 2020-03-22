/**
 * <p>
 * Title: ProductService.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月16日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.service;

import java.util.List;
import com.zl.webshop.dto.ProductExecution;
import com.zl.webshop.entity.Product;
import com.zl.webshop.entity.ProductCategory;

/**
 * <p>
 * Title: ProductService
 * </p>
 * <p>
 * Description: 产品业务接口
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月16日
 *         </p>
 */
public interface ProductService {
  /**
   * 
   * <p>
   * Title: getCategories
   * </p>
   * <p>
   * Description: 获取所有产品类别
   * </p>
   * 
   * @return 所有产品类别
   */
  List<ProductCategory> getCategories();

  /**
   * 
   * <p>
   * Title: getProducts
   * </p>
   * <p>
   * Description: 通过类别序号获取产品
   * </p>
   * 
   * @param categoryId 产品类别序号
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return ProductExecution
   */
  ProductExecution getProducts(int categoryId, int offset, int limit);

  /**
   * 
   * <p>
   * Title: searchByText
   * </p>
   * <p>
   * Description: 通过关键词获得产品
   * </p>
   * 
   * @param searchText 搜索关键词
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 产品列表
   */
  List<Product> searchByText(String searchText, int offset, int limit);
}
