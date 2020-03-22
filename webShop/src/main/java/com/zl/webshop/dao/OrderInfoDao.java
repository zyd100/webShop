/**
 * <p>
 * Title: OrderInfoDao.java
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
import com.zl.webshop.entity.OrderInfo;

/**
 * <p>
 * Title: OrderInfoDao
 * </p>
 * <p>
 * Description: 订单信息Dao
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月14日
 *         </p>
 */
public interface OrderInfoDao {
  /**
   * 
   * <p>
   * Title: count
   * </p>
   * <p>
   * Description: 获取用订单总数
   * </p>
   * 
   * @return 订单总数
   */
  int count();

  /**
   * 
   * <p>
   * Title: countByUserName
   * </p>
   * <p>
   * Description: 根据用户名获取订单总数
   * </p>
   * @param userName 用户名
   *  @return 订单数
   */
  int countByUserName(@Param("userName") String userName);

  /**
   * 
   * <p>
   * Title: queryAll
   * </p>
   * <p>
   * Description: 查询所有订单信息
   * </p>
   * 
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 订单信息列表
   */
  List<OrderInfo> queryAll(@Param("offset") int offset, @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: queryByUserName
   * </p>
   * <p>
   * Description: 根据用户名查询订单信息
   * </p>
   * 
   * @param userName 用户名
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 订单信息列表
   */
  List<OrderInfo> queryByUserName(@Param("userName") String userName, @Param("offset") int offset,
      @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: queryByOrderNum
   * </p>
   * <p>
   * Description: 根据订单编号查询订单信息
   * </p>
   * 
   * @param orderNum 订单编号
   * @return OrderInfo 订单信息对象
   */
  OrderInfo queryByOrderNum(@Param("orderNum") String orderNum);

  /**
   * 
   * <p>
   * Title: addOrderInfo
   * </p>
   * <p>
   * Description: 添加一个订单信息
   * </p>
   * 
   * @param orderInfo 订单信息对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int addOrderInfo(@Param("orderInfo") OrderInfo orderInfo);

  /**
   * 
   * <p>
   * Title: updateOrderInfo
   * </p>
   * <p>
   * Description: 更新一个订单信息
   * </p>
   * 
   * @param orderInfo 订单信息对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int updateOrderInfo(@Param("orderInfo") OrderInfo orderInfo);

  /**
   * 
   * <p>
   * Title: deleteOrderInfo
   * </p>
   * <p>
   * Description: 删除一个订单信息
   * </p>
   * 
   * @param orderInfo 订单信息对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteOrderInfo(@Param("orderInfo") OrderInfo orderInfo);

  /**
   * 
   * <p>
   * Title: deleteOrderInfoByUserName
   * </p>
   * <p>
   * Description: 根据用户名删除订单
   * </p>
   * 
   * @param userName 用户名
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteOrderInfoByUserName(@Param("userName") String userName);
}
