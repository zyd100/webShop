package com.zl.webshop.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;
import com.zl.webshop.entity.OrderHistory;
/**
 * 
　 * <p>Title: OrderHistoryDaoTest</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月15日 </p>
 */
@Deprecated
public class OrderHistoryDaoTest extends BaseTest{

  @Autowired
  private OrderHistoryDao orderHistoryDao;
  
  @Test@Ignore
  public void testAddOrderHistory() {
    OrderHistory orderHistory=new OrderHistory();
    orderHistory.setCreateTime(LocalDateTime.now());
    String orderNum=UUID.randomUUID().toString().replace("-", "");
    orderHistory.setOrderNum(orderNum);
    orderHistory.setStatus(1);
    orderHistory.setUpdateUserName("zydadmin");
    orderHistoryDao.addOrderHistory(orderHistory);
  }
  @Test@Ignore
  public void testUpdateOrderHistory() {
    OrderHistory orderHistory=orderHistoryDao.queryById(2);
    orderHistory.setUpdateUserName("admin");
    int count=orderHistoryDao.updateOrderHistory(orderHistory);
    System.out.println(count);
  }
  @Test@Ignore
  public void testDeleteOrderHistory() {
    OrderHistory orderHistory=orderHistoryDao.queryById(2);
    int count=orderHistoryDao.deleteOrderHistory(orderHistory);
    System.out.println(count);
  }
  @Test@Ignore
  public void testQueryAll() {
    List<OrderHistory>list=orderHistoryDao.queryAll(0, 10);
    System.out.println(list);
  }
  @Test@Ignore
  public void testQueryById() {
    OrderHistory orderHistory=orderHistoryDao.queryById(1);
    System.out.println(orderHistory);
  }
  @Test@Ignore
  public void testQueryByOrderNum() {
    OrderHistory orderHistory=orderHistoryDao.queryById(1);
     orderHistory=orderHistoryDao.queryByOrderNum(orderHistory.getOrderNum());
    System.out.println(orderHistory);
  }
  @Test@Ignore
  public void testQueryAllByUpdateUserName() {
    List<OrderHistory>list=orderHistoryDao.queryAllByUpdateUserName("admin", 0, 10);
    System.out.println(list);
  }
  @Test@Ignore
  public void testDeleteByOrderNum() {
    orderHistoryDao.deleteByOrderNum("asd");
  }
  @Test@Ignore
  public void testFuzzy() {
    System.out.println(orderHistoryDao.fuzzyQueryAllByText("d",0,10));
    System.out.println(orderHistoryDao.fuzzyCount("d"));
  }
}
