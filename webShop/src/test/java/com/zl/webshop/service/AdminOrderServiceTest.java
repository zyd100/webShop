/** 
　 * <p>Title: AdminOrderServiceTest.java</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年4月1日 </p>
　 * @version 1.0 
*/
package com.zl.webshop.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;
import com.zl.webshop.enums.OrderStatusEnum;


/** 
　 * <p>Title: AdminOrderServiceTest</p> 
　 * <p>Description: 管理产品测试</p> 
　 * @author zyd 
　 * <p>创建日期：2020年4月1日 </p>
*/
public class AdminOrderServiceTest extends BaseTest{
  @Autowired
  private AdminOrderService adminOrderService;

  @Test@Ignore
  public void testGetOrderInfos() {
    adminOrderService.getOrderInfos(null, 0, 10).stream().forEach(System.out::println);
  }
  @Test@Ignore
  public void testUpdateOrderStatus() {
   adminOrderService.updateOrderStatus(OrderStatusEnum.ORDER_PLACED,  adminOrderService.getOrderInfos(null, 0, 10).get(0).getOrderNum());
  }
  @Test@Ignore
  public void testDeleteOrderHistory() {
    adminOrderService.deleteOrderHistory("dd337ef2d1134475a00e20257515e0fa");
  }
}
