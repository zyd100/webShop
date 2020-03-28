/**
 * <p>
 * Title: OrderService.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月16日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.service;

import com.zl.webshop.dto.OrderExecution;
import com.zl.webshop.entity.Contact;
import com.zl.webshop.entity.OrderItem;
import com.zl.webshop.enums.OrderStatusEnum;

/**
 * <p>
 * Title: OrderService
 * </p>
 * <p>
 * Description: 订单业务接口
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月16日
 *         </p>
 */
public interface OrderService {
  /**
   * 
   * <p>
   * Title: addToCart
   * </p>
   * <p>
   * Description: 添加商品入购物车
   * </p>
   * 
   * @param orderItem 商品以及购买数量
   * @param userName 用户名
   * @return 大于0表示添加成功
   */
  int addToCart(OrderItem orderItem, String userName);

  /**
   * 
   * <p>
   * Title: addToStar
   * </p>
   * <p>
   * Description: 添加商品入收藏夹
   * </p>
   * 
   * @param orderItem 商品
   * @param userName 用户名
   * @return 大于0表示添加成功
   */
  int addToStar(OrderItem orderItem, String userName);

  /**
   * 
   * <p>
   * Title: removeFromCart
   * </p>
   * <p>
   * Description: 删除一个商品从购物车内
   * </p>
   * 
   * @param orderItem 商品以及购买数量
   * @param userName 用户名
   * @return 大于0表示添加成功
   */
  int removeFromCart(OrderItem orderItem, String userName);

  /**
   * 
   * <p>
   * Title: removeFromStar
   * </p>
   * <p>
   * Description: 从收藏夹内删除一个商品
   * </p>
   * 
   * @param orderItem 商品以及购买数量
   * @param userName 用户名
   * @return 大于0表示添加成功
   */
  int removeFromStar(OrderItem orderItem, String userName);

  /**
   * 
   * <p>
   * Title: updateProductQuantityInCart
   * </p>
   * <p>
   * Description: 更新在购物车中的商品数量
   * </p>
   * 
   * @param orderItem 商品条目
   * @param userName 用户名
   * @return 大于0表示更新成功
   */
  int updateProductQuantityInCart(OrderItem orderItem, String userName);

  /**
   * 
   * <p>
   * Title: buyOneNow
   * </p>
   * <p>
   * Description: 立即下单购买一个商品
   * </p>
   * 
   * @param orderItem 商品条目
   * @param userName 用户名
   * @param contact 联系信息
   * @param message 备注
   * @return OrderExecution
   */
  OrderExecution buyOneNow(OrderItem orderItem, String userName, Contact contact, String message);

  /**
   * 
   * <p>
   * Title: buyGoods
   * </p>
   * <p>
   * Description: 购买选定的复数商品
   * </p>
   * 
   * @param orderExecution 订单DTO 保存商品清单和相关用户信息
   * @return OrderExecution
   */
  OrderExecution buyGoods(OrderExecution orderExecution);

  /**
   * 
   * <p>
   * Title: getCart
   * </p>
   * <p>
   * Description: 获取购物车内容
   * </p>
   * 
   * @param userName 用户名
   * @return OrderExecution
   */
  OrderExecution getCart(String userName);

  /**
   * 
   * <p>
   * Title: getStar
   * </p>
   * <p>
   * Description: 获取收藏夹列表
   * </p>
   * 
   * @param userName 用户名
   * @return OrderExecution
   */
  OrderExecution getStar(String userName);

  /**
   * 
   * <p>
   * Title: getOderInfoByUserName
   * </p>
   * <p>
   * Description: 根据用户名来查询订单信息，同时支持使用 OrderStatusEnum 来过滤订单
   * </p>
   * 
   * @param userName 用户名
   * @param status 订单状态
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return OrderExecution
   */
  OrderExecution getOderInfoByUserName(String userName, OrderStatusEnum status, int offset,
      int limit);

  /**
   * 
   * <p>
   * Title: getOrderDetail
   * </p>
   * <p>
   * Description: 获取此订单编号的详细信息
   * </p>
   * 
   * @param orderNum 订单编号
   * @param userName 用户名
   * @return OrderExecution
   */
  OrderExecution getOrderDetail(String orderNum,String userName);

  /**
   * 
   * <p>
   * Title: deleteOrder
   * </p>
   * <p>
   * Description: 删除有关此订单的一切信息
   * </p>
   * @param orderNum 订单编号 
   * @return 大于0表示删除成功
   */
  int deleteOrder(String orderNum);
}
