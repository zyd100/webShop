/**
 * <p>
 * Title: AdminCategoryService.java
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
import com.zl.webshop.entity.ProductCategory;

/**
 * <p>
 * Title: AdminCategoryService
 * </p>
 * <p>
 * Description: 管理员商品分类业务接口
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月30日
 *         </p>
 */
public interface AdminCategoryService {

  /**
   * 
   * <p>
   * Title: getCategories
   * </p>
   * <p>
   * Description: 获取分类
   * </p>
   * 
   * @param searchText 搜索关键词 可不填
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 分类列表
   */
  List<ProductCategory> getCategories(String searchText, int offset, int limit);

  /**
   * 
   * <p>
   * Title: addCategory
   * </p>
   * <p>
   * Description: 新增一个类别
   * </p>
   * 
   * @param category 类别
   * @return 返回类别表最新数量
   */
  int addCategory(ProductCategory category);

  /**
   * 
   * <p>
   * Title: updateCategory
   * </p>
   * <p>
   * Description: 更新一个类别
   * </p>
   * 
   * @param category 类别
   * @return 更新后的此类别的数据
   */
  ProductCategory updateCategory(ProductCategory category);

  /**
   * 
   * <p>
   * Title: deleteCateGory
   * </p>
   * <p>
   * Description: 删除一个类别
   * </p>
   * 
   * @param category 类别
   * @return 返回类别表最新数量
   */
  int deleteCateGory(ProductCategory category);
}
