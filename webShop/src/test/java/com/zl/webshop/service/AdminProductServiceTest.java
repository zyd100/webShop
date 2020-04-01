package com.zl.webshop.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;
import com.zl.webshop.entity.Product;

public class AdminProductServiceTest extends BaseTest{
  @Autowired
  private AdminProductService productService;
  
  @Test@Ignore
  public void testGetProducts() {
    System.out.println(productService.getProducts(null, 0, 10));
  }
  @Test@Ignore
  public void testUpdateProduct() {
    Product product=new Product();
    product.setId(7);
    product.setShopPrice(-100);
    product.setCategoryId(1);
    productService.updateProduct(product);
  }
  @Test@Ignore
  public void testDeleteProduct() {
    Product product=new Product();
    product.setId(11);
    productService.deleteProduct(product);
  }
  @Test@Ignore
  public void testAddProduct() {
    Product product=new Product();
    product.setShopPrice(500);
    product.setQuantity(20);
    product.setCategoryId(1);
    product.setProductName("≤‚ ‘…Ã∆∑");
    productService.addProduct(product);
  }
}
