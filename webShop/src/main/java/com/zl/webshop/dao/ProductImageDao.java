/**
 * <p>
 * Title: ProductImageDao.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年4月3日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.zl.webshop.entity.ProductImage;

/**
 * <p>
 * Title: ProductImageDao
 * </p>
 * <p>
 * Description: 产品图片表Dao
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年4月3日
 *         </p>
 */
public interface ProductImageDao {

  /**
   * 
   * <p>
   * Title: count
   * </p>
   * <p>
   * Description:获取图片总数
   * </p>
   * 
   * @return 图片总数
   */
  int count();

  /**
   * 
   * <p>
   * Title: countByProductId
   * </p>
   * <p>
   * Description: 根据产品序号计算图片总数
   * </p>
   * 
   * @param productId 产品序号
   * @return 图片总数
   */
  int countByProductId(@Param("productId") long productId);

  /**
   * 
   * <p>
   * Title: queryAll
   * </p>
   * <p>
   * Description: 获取所有图片
   * </p>
   * 
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 图片列表
   */
  List<ProductImage> queryAll(@Param("offset") int offset, @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: queryByProductId
   * </p>
   * <p>
   * Description: 根据产品序号获取图片
   * </p>
   * 
   * @param productId 产品序号
   * @return 图片列表
   */
  List<ProductImage> queryByProductId(@Param("productId") long productId);

  /**
   * 
   * <p>
   * Title: deleteImage
   * </p>
   * <p>
   * Description: 根据序号删除图片
   * </p>
   * 
   * @param id 序号
   * @return 大于等于1表示删除成功
   */
  int deleteImage(@Param("id") long id);

  /**
   * 
   * <p>
   * Title: deleteByProductId
   * </p>
   * <p>
   * Description: 根据产品序号批量删除图片
   * </p>
   * 
   * @param productId 产品序号
   * @return 大于等于1表示删除成功
   */
  int deleteByProductId(@Param("productId") long productId);

  /**
   * 
   * <p>
   * Title: addImage
   * </p>
   * <p>
   * Description: 添加一个图片
   * </p>
   * 
   * @param productImage 产品图片对象
   * @return 大于等于1表示添加成功
   */
  int addImage(@Param("productImage") ProductImage productImage);

  /**
   * 
   * <p>
   * Title: updateImage
   * </p>
   * <p>
   * Description: 更新此序号保存的图片
   * </p>
   * 
   * @param id 序号
   * @param image 图片
   * @return 大于等于1表示更新成功
   */
  int updateImage(@Param("id") long id, @Param("image") String image);
}
