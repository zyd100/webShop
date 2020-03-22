package com.zl.webshop.dao;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;
import com.zl.webshop.entity.Product;
/**
 * 
　 * <p>Title: ProductDaoTest</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月15日 </p>
 */
@Deprecated
public class ProductDaoTest extends BaseTest {
  @Autowired
  private ProductDao productDao;
  
  @Test@Ignore
  public void testAddProduct() {
    Product product=new Product();
    product.setProductName("商666");
    product.setCategoryId(1);
    product.setPrice(50);
    product.setShopPrice(500);
    product.setQuantity(100);
    int count=productDao.addProduct(product);
    System.out.println(count);
  }
  @Test@Ignore
  public void testUpdateProduct() {
    Product product=productDao.queryById(1);
    product.setCategoryId(2);
    int count=productDao.updateProduct(product);
    System.out.println(count);
  }
  @Test@Ignore
  public void testDeleteProduct() {
    Product product=productDao.queryById(2);
    int count=productDao.deleteProduct(product);
    System.out.println(count);
  }
  @Test
  public void testQueryById() {
    Product product=productDao.queryById(1);
    System.out.println(product);
  }
  @Test@Ignore
  public void testQueryAll() {
    List<Product>list=productDao.queryAll(0, 10);
    System.out.println(list);
  }
  @Test@Ignore
  public void testFuzzyQueryAllByText() {
    List<Product>list=productDao.fuzzyQueryAllByText("D商", 0, 10);
    System.out.println(list);
  }
}
