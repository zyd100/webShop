/**
 * <p>
 * Title: ProductImageDaoTest.java
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

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;
import com.zl.webshop.entity.ProductImage;

/**
 * <p>
 * Title: ProductImageDaoTest
 * </p>
 * <p>
 * Description: 产品图片表Dao测试
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年4月3日
 *         </p>
 */
public class ProductImageDaoTest extends BaseTest {
  @Autowired
  private ProductImageDao productImageDao;

  @Test
  @Ignore
  public void testCount() {
    System.out.println(productImageDao.count());
  }

  @Test
  @Ignore
  public void testCountByProductId() {
    System.out.println(productImageDao.countByProductId(7));
  }

  @Test
  @Ignore
  public void testQueryAll() {
    System.out.println(productImageDao.queryAll(0, 10));
  }

  @Test
  @Ignore
  public void testQueryByProductId() {
    System.out.println(productImageDao.queryByProductId(7));
  }

  @Test
  public void testDeleteImage() {
    productImageDao.deleteImage(2);
  }

  @Test
  @Ignore
  public void testDeleteByProductId() {
    productImageDao.deleteByProductId(7);
  }

  @Test
  @Ignore
  public void testAddImage() {
    ProductImage productImage = new ProductImage();
    productImage.setProductId(7);
    productImage.setImage("");
    productImageDao.addImage(productImage);
  }

  @Test
  @Ignore
  public void testUpdateImage() {
    productImageDao.updateImage(1, "update");
  }
}
