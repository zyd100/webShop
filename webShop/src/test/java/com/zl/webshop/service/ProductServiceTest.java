/** 
　 * <p>Title: ProductServiceTest.java</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月23日 </p>
　 * @version 1.0 
*/
package com.zl.webshop.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;

/** 
　 * <p>Title: ProductServiceTest</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月23日 </p>
*/
public class ProductServiceTest extends BaseTest{

  @Autowired
  private ProductService productService;
  
  @Test
  public void testGetCategories() {
    System.out.println(productService.getCategories()); 
  }
  @Test
  public void testGetProducts() {
    System.out.println(productService.getProducts(1, 0, 1000));
  }
  @Test
  public void testSearchByText() {
    System.out.println(productService.searchByText("电脑", 0, 1000));
  }
  @Test@Ignore
  public void testGetProduct() {
    System.out.println(productService.getProduct(8, "超级电脑"));
  }
}
