package com.zl.webshop.dao;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;
import com.zl.webshop.entity.OrderInfo;
/**
 * 
　 * <p>Title: OrderInfoTest</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月15日 </p>
 */
@Deprecated
public class OrderInfoTest extends BaseTest{

  @Autowired
  private OrderInfoDao orderInfoDao;
  
  @Test@Ignore
  public void testQueryAll() {
    List<OrderInfo>list=orderInfoDao.queryAll(0, 10);
    System.out.println(list);
  }
  @Test@Ignore
  public void testQueryByUserName() {
    List<OrderInfo> list=orderInfoDao.queryByUserName("testId", 0, 10);
    System.out.println(list.toString());
  }
  @Test@Ignore
  public void testQueryByOrderNum() {
    OrderInfo orderInfo=orderInfoDao.queryByOrderNum("000000");
    System.out.println(orderInfo);
  }
  @Test@Ignore
  public void testAddOrderInfo() {
    OrderInfo orderInfo=new OrderInfo();
    orderInfo.setUserName("testId");
    orderInfo.setOrderNum("000000");
    orderInfo.setStatus(0);

    int count = orderInfoDao.addOrderInfo(orderInfo);
    System.out.println(count);
  }
  @Test@Ignore
  public void testUpdateOrderInfo() {
    /*OrderInfo orderInfo=orderInfoDao.queryByOrderNum("000000");
    orderInfo.setMessage("aoaoao");
    int count = orderInfoDao.updateOrderInfo(orderInfo);
    System.out.println(count);*/
    OrderInfo orderInfo=new OrderInfo();
    orderInfo.setOrderNum("dd337ef2d1134475a00e20257515e0fa");
    orderInfo.setStatus(-1);
    orderInfo.setMessage("");
    orderInfoDao.updateOrderInfo(orderInfo);
  }
  @Test@Ignore
  public void testDeleteOrderInfo() {
    OrderInfo orderInfo=orderInfoDao.queryByOrderNum("000000");
    int count = orderInfoDao.deleteOrderInfo(orderInfo);
    System.out.println(count);
  }
  @Test@Ignore
  public void testDeleteByOrderNum() {
    orderInfoDao.deleteByOrderNum("aaa");
  }
  @Test
  public void testFuzzy() {
    System.out.println(orderInfoDao.fuzzyQueryAllByText("d",0,10));
    System.out.println(orderInfoDao.fuzzyCount("d"));
  }
}
