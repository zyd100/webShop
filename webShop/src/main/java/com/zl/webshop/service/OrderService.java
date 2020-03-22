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
   * @return 大于等于0表示添加成功
   */
  int addToCart(OrderItem orderItem, String userName);

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
   * @return 大于等于0表示添加成功
   */
  int removeFromCart(OrderItem orderItem, String userName);

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
   * @return 大于等于0表示更新成功
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
  OrderExecution buyOneNow(OrderItem orderItem, String userName, Contact contact,String message);

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
}
