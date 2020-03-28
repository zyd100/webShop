/**
 * <p>
 * Title: ProductDao.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd @date 2020年3月12日 @version 1.0
 */
package com.zl.webshop.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.zl.webshop.entity.Product;

/**
 * <p>
 * Title: ProductDao
 * </p>
 * <p>
 * Description: 产品表Dao
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月12日
 *         </p>
 */
public interface ProductDao {
  /**
   * 
   * <p>
   * Title: randomByCategoryId
   * </p>
   * <p>
   * Description: 随机获取相同类别的产品
   * </p>
   * 
   * @param categoryId 产品类别序号
   * @param limit 查询条数
   * @return 产品列表
   */
  List<Product> randomByCategoryId(@Param("categoryId") long categoryId, @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: count
   * </p>
   * <p>
   * Description: 获取产品总数
   * </p>
   * 
   * @return 产品总数
   */
  int count();

  /**
   * 
   * <p>
   * Title: fuzzyCount
   * </p>
   * <p>
   * Description: 获取模糊查询的个数
   * </p>
   * 
   * @param searchText 搜索关键词
   * @return 个数
   */
  int fuzzyCount(@Param("searchText") String searchText);

  /**
   * 
   * <p>
   * Title: queryAll
   * </p>
   * <p>
   * Description: 查询所有产品
   * </p>
   * 
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 产品列表
   */
  List<Product> queryAll(@Param("offset") int offset, @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: randomAll
   * </p>
   * <p>
   * Description: 随机获取产品
   * </p>
   * 
   * @param limit 查询条数
   * @return 产品列表
   */
  List<Product> randomAll(@Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: fuzzyQueryAllByText
   * </p>
   * <p>
   * Description: 模糊查询所有产品
   * </p>
   * 
   * @param searchText 关键词
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 产品列表
   */
  List<Product> fuzzyQueryAllByText(@Param("searchText") String searchText,
      @Param("offset") int offset, @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: queryById
   * </p>
   * <p>
   * Description: 使用id查询单个产品
   * </p>
   * 
   * @param id 产品序号
   * @return 产品对象
   */
  Product queryById(@Param("id") long id);

  /**
   * 
   * <p>
   * Title: queryByCategoryId
   * </p>
   * <p>
   * Description: 根据产品类别获取产品
   * </p>
   * 
   * @param categoryId 类别序号
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 产品列表
   */
  List<Product> queryByCategoryId(@Param("categoryId") long categoryId, @Param("offset") int offset,
      @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: queryByProductName
   * </p>
   * <p>
   * Description: 使用产品名查询单个产品
   * </p>
   * 
   * @param productName 产品名
   * @return 产品对象
   */
  List<Product> queryByProductName(@Param("productName") String productName);

  /**
   * 
   * <p>
   * Title: addProduct
   * </p>
   * <p>
   * Description: 添加产品
   * </p>
   * 
   * @param product 产品对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int addProduct(@Param("product") Product product);

  /**
   * 
   * <p>
   * Title: updateProduct
   * </p>
   * <p>
   * Description: 更新产品
   * </p>
   * 
   * @param product 产品对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int updateProduct(@Param("product") Product product);

  /**
   * 
   * <p>
   * Title: deleteProduct
   * </p>
   * <p>
   * Description: 删除产品
   * </p>
   * 
   * @param product 产品对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteProduct(@Param("product") Product product);
}
