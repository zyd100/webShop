/**
 * <p>
 * Title: OrderItemDao.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月14日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.zl.webshop.entity.OrderItem;

/**
 * <p>
 * Title: OrderItemDao
 * </p>
 * <p>
 * Description: 订单条目Dao
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月14日
 *         </p>
 */
public interface OrderItemDao {
  /**
   * 
   * <p>
   * Title: count
   * </p>
   * <p>
   * Description: 获取订单条目总数
   * </p>
   * 
   * @return 订单条目总数
   */
  int count();

  /**
   * 
   * <p>
   * Title: countByOrderNum
   * </p>
   * <p>
   * Description: 根据订单编号获取条目数
   * </p>
   * 
   * @param orderNum 订单编号
   * @return 条目数
   */
  int countByOrderNum(@Param("orderNum") String orderNum);

  /**
   * 
   * <p>
   * Title: queryAll
   * </p>
   * <p>
   * Description: 通过订单号以及用户名查询订单条目
   * </p>
   * 
   * @param userName 用户名
   * @param orderNum 订单编号
   * @return 订单条目列表
   */
  List<OrderItem> queryByUserNameAndOrderNum(@Param("userName") String userName,
      @Param("orderNum") String orderNum);

  /**
   * 
   * <p>
   * Title: queryById
   * </p>
   * <p>
   * Description:通过订单条目序号查询
   * </p>
   * 
   * @param id 序号
   * @return OrderItem 实体对象
   */
  OrderItem queryById(@Param("id") long id);

  /**
   * 
   * <p>
   * Title: addOrderItem
   * </p>
   * <p>
   * Description: 添加订单条目
   * </p>
   * 
   * @param orderItem 订单条目实体对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int addOrderItem(@Param("orderItem") OrderItem orderItem);

  /**
   * 
   * <p>
   * Title: updateOrderItemByProduct
   * </p>
   * <p>
   * Description: 根据产品序号批量修改订单条目的产品信息 Ps:仅修改产品名，产品数量，产品单价
   * </p>
   * 
   * @param productId 产品序号
   * @param orderItem 订单条目实体对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int updateOrderItemByProduct(@Param("productId") long productId,
      @Param("orderItem") OrderItem orderItem);

  /**
   * 
   * <p>
   * Title: updateOrderItem
   * </p>
   * <p>
   * Description: 更新单个订单条目信息
   * </p>
   * 
   * @param orderItem 订单条目实体对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int updateOrderItem(@Param("orderItem") OrderItem orderItem);

  /**
   * 
   * <p>
   * Title: deleteOrdetItem
   * </p>
   * <p>
   * Description: 删除一个订单条目
   * </p>
   * 
   * @param orderItem 订单条目实体对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteOrderItem(@Param("orderItem") OrderItem orderItem);

  /**
   * 
   * <p>
   * Title: deleteOrderItemByProduct
   * </p>
   * <p>
   * Description: 根据产品序号批量删除订单条目
   * </p>
   * 
   * @param productId 产品序号
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteOrderItemByProduct(@Param("productId") long productId);

  /**
   * 
   * <p>
   * Title: deleteOrderItemByUserName
   * </p>
   * <p>
   * Description: 根据用户名删除订单条目
   * </p>
   * 
   * @param userName 用户名
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteOrderItemByUserName(@Param("userName") String userName);

  /**
   * 
   * <p>
   * Title: deleteOrderItemByOrderNum
   * </p>
   * <p>
   * Description: 根据订单编号删除订单条目
   * </p>
   * 
   * @param orderNum 订单编号
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteOrderItemByOrderNum(@Param("orderNum") String orderNum);
}
