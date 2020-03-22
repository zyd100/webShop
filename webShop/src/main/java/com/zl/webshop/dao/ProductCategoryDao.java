/**
 * <p>
 * Title: ProductCategoryDao.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 * @date 2020年3月13日
 * @version 1.0
 */
package com.zl.webshop.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.zl.webshop.entity.ProductCategory;

/**
 * <p>
 * Title: ProductCategoryDao
 * </p>
 * <p>
 * Description: 商品类别表Dao
 * </p>
 * 
 * @author zyd
 * <p>创建日期：2020年3月13日</p>
 */
public interface ProductCategoryDao {
  /**
   * 
   * <p>
   * Title: count
   * </p>
   * <p>
   * Description: 获取产品类别总数
   * </p>
   * @return 产品类别总数
   */
  int count();
  /**
   * 
   * <p>
   * Title: queryAll
   * </p>
   * <p>
   * Description: 查询所有产品类别
   * </p>
   * 
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 类别列表
   */
  List<ProductCategory> queryAll(@Param("offset") int offset, @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: queryById
   * </p>
   * <p>
   * Description: 使用id查询产品类别
   * </p>
   * 
   * @param id 产品类别序号
   * @return 产品类别对象
   */
  ProductCategory queryById(@Param("id") long id);

  /**
   * 
   * <p>
   * Title: addProductCategory
   * </p>
   * <p>
   * Description: 添加一个产品类别
   * </p>
   * 
   * @param productCategory 产品类别对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int addProductCategory(@Param("productCategory") ProductCategory productCategory);

  /**
   * 
   * <p>
   * Title: updateProductCategory
   * </p>
   * <p>
   * Description: 更新一个产品类别
   * </p>
   * 
   * @param productCategory 产品类别对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int updateProductCategory(@Param("productCategory") ProductCategory productCategory);

  /**
   * 
   * <p>
   * Title: deleteProductCategory
   * </p>
   * <p>
   * Description: 删除一个产品类别
   * </p>
   * 
   * @param productCategory 产品类别对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteProductCategory(@Param("productCategory") ProductCategory productCategory);
}
