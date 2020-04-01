/**
 * <p>
 * Title: AdminOrderService.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月30日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.service;

import java.util.List;
import com.zl.webshop.dto.OrderExecution;
import com.zl.webshop.enums.OrderStatusEnum;

/**
 * <p>
 * Title: AdminOrderService
 * </p>
 * <p>
 * Description: 管理员订单业务接口
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月30日
 *         </p>
 */
public interface AdminOrderService {
  /**
   * 
   * <p>
   * Title: getOrderInfos
   * </p>
   * <p>
   * Description: 获取订单历史
   * </p>
   * 
   * @param searchText 搜索关键词 选填
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 订单历史表
   */
  List<OrderExecution> getOrderInfos(String searchText, int offset, int limit);

  /**
   * 
   * <p>
   * Title: getOrderDetail
   * </p>
   * <p>
   * Description: 获取订单详细信息
   * </p>
   * 
   * @param orderNum 订单编号
   * @return 订单详细信息
   */
  OrderExecution getOrderDetail(String orderNum);

  /**
   * 
   * <p>
   * Title: updateOrderStatus
   * </p>
   * <p>
   * Description: 更新订单状态
   * </p>
   * 
   * @param statusEnum 订单状态
   * @param orderNum 订单编号
   * @return 更新后的订单信息
   */
  OrderExecution updateOrderStatus(OrderStatusEnum statusEnum, String orderNum);

  /**
   * 
   * <p>
   * Title: deleteOrderHistory
   * </p>
   * <p>
   * Description: 删除订单历史
   * </p>
   * 
   * @param orderNum 订单编号
   * @return 返回最新的订单历史总数
   */
  int deleteOrderHistory(String orderNum);
}
