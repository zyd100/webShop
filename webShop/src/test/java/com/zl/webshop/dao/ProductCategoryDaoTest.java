package com.zl.webshop.dao;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;
import com.zl.webshop.entity.ProductCategory;
/**
 * 
　 * <p>Title: ProductCategoryDaoTest</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月15日 </p>
 */
@Deprecated
public class ProductCategoryDaoTest extends BaseTest{

  @Autowired
  ProductCategoryDao productCategoryDao;
  
  @Test@Ignore
  public void testAddProductCategory() {
    ProductCategory productCategory=new ProductCategory();
    productCategory.setCategoryName("手机");
    productCategory.setDescription("手机以及相关配件");
    productCategory.setSortOrder(5);
    int count=productCategoryDao.addProductCategory(productCategory);
    System.out.println(count);
  }
  @Test@Ignore
  public void testUpdateProductCategory() {
    ProductCategory productCategory=productCategoryDao.queryById(2);
    productCategory.setDescription("手机一条龙");
    int count=productCategoryDao.updateProductCategory(productCategory);
    System.out.println(count);
  }
  @Test@Ignore
  public void testQueryAll() {
    List<ProductCategory>list=productCategoryDao.queryAll(0, 10);
    System.err.println(list);
  }
  @Test@Ignore
  public void testQueryById() {
    ProductCategory productCategory=productCategoryDao.queryById(2);
    System.out.println(productCategory);
  }
  @Test
  public void testDeleteProductCategory() {
    ProductCategory productCategory=productCategoryDao.queryById(2);
    int count=productCategoryDao.deleteProductCategory(productCategory);
    System.out.println(count);
  }
}
