package com.zl.webshop.dao;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;
import com.zl.webshop.entity.OrderItem;
/**
 * 
　 * <p>Title: OrderItemDaoTest</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月15日 </p>
 */
@Deprecated
public class OrderItemDaoTest extends BaseTest{

  @Autowired
  private OrderItemDao orderItemDao;
  
  @Test@Ignore
  public void testQueryByUserNameAndOrderNum() {
    List<OrderItem> list=orderItemDao.queryByUserNameAndOrderNum("zydadmin", "textordernum123");
    System.out.println(list);
  }
  @Test@Ignore
  public void testQueryById() {
    OrderItem orderItem=orderItemDao.queryById(2);
    System.out.println(orderItem);
  }
  @Test@Ignore
  public void testAddOrderItem() {
    OrderItem orderItem=new OrderItem();
    orderItem.setOrderNum("textordernum123");
    orderItem.setPrice(50);
    orderItem.setProductId(2);
    orderItem.setProductName("testProduct");
    orderItem.setQuantity(2);
    orderItem.setUserName("zydadmin");
    orderItemDao.addOrderItem(orderItem);
  }
  @Test@Ignore
  public void testUpdateOrderItemByProduct() {
    OrderItem orderItem=new OrderItem();
    orderItem.setPrice(100);
    orderItem.setProductName("批量修改测试");
    orderItem.setQuantity(200);
    int count=orderItemDao.updateOrderItemByProduct(2, orderItem);
    System.out.println(count);
  }
  @Test@Ignore
  public void testUpdateOrderItem() {
    List<OrderItem> list=orderItemDao.queryByUserNameAndOrderNum("zydadmin", "textordernum123");
    OrderItem orderItem=list.stream().filter(x->x.getId()==5).findAny().orElse(null);
    orderItem.setQuantity(500);
    int count=orderItemDao.updateOrderItem(orderItem);
    System.out.println(count);
  }
  @Test@Ignore
  public void testDeleteOrdetItem() {
    OrderItem orderItem=orderItemDao.queryById(1);
    int count=orderItemDao.deleteOrderItem(orderItem);
    System.out.println(count);
  }
  @Test@Ignore
  public void testDeleteOrderItemByProduct() {
    int count=orderItemDao.deleteOrderItemByProduct(2);
    System.out.println(count);
  }
}
