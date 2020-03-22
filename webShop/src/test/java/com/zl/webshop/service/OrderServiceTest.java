/**
 * <p>
 * Title: OrderServiceTest.java
 * </p>
 * <p>
 * Description:
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月22日
 * </p>
 * @version 1.0
 */
package com.zl.webshop.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;
import com.zl.webshop.dao.OrderItemDao;
import com.zl.webshop.dao.ProductDao;
import com.zl.webshop.entity.Contact;
import com.zl.webshop.entity.OrderItem;
import com.zl.webshop.entity.Product;

/**
 * <p>
 * Title: OrderServiceTest
 * </p>
 * <p>
 * Description: 订单业务测试类
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月22日
 * </p>
 */
public class OrderServiceTest extends BaseTest {

  @Autowired
  private OrderService orderService;
  @Autowired
  private ProductDao productDao;
  @Autowired
  private OrderItemDao orderItemDao;

  @Test
  @Ignore
  public void testAddToCart() {
    OrderItem orderItem = new OrderItem();
    Product product = productDao.queryById(7);
    orderItem.setProductName(product.getProductName());
    orderItem.setProductId(product.getId());
    orderItem.setPrice(product.getPrice());
    orderItem.setQuantity(1);
    orderService.addToCart(orderItem, "testId");
  }

  @Test
  @Ignore
  public void testRemoveFromCart() {
    OrderItem orderItem = orderItemDao.queryById(8);
    orderService.removeFromCart(orderItem, "testId");
  }

  @Test
  @Ignore
  public void testUpdateProductQuantityInCart() {
    OrderItem orderItem = orderItemDao.queryById(8);
    orderItem.setQuantity(1);
    orderService.updateProductQuantityInCart(orderItem, orderItem.getUserName());
  }

  @Test@Ignore
  public void testGetCart() {
    orderService.getCart("testId").getOrderItemList().forEach(System.out::println);
  }
  @Test@Ignore
  public void testBuyOneNow() {
    OrderItem orderItem = orderItemDao.queryById(9);
    Contact contact=new Contact();
    orderService.buyOneNow(orderItem, orderItem.getUserName(), contact, "testMesssage");
  }
}
