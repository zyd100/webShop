/**
 * <p>
 * Title: OrderHistoryDao.java
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
import com.zl.webshop.entity.OrderHistory;

/**
 * <p>
 * Title: OrderHistoryDao
 * </p>
 * <p>
 * Description: 订单历史表Dao
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期： 2020年3月14日
 *         </p>
 */
public interface OrderHistoryDao {
  /**
   * 
   * <p>
   * Title: count
   * </p>
   * <p>
   * Description: 获取用订单历史总数
   * </p>
   * 
   * @return 订单历史总数
   */
  int count();

  /**
   * 
   * <p>
   * Title: queryAll
   * </p>
   * <p>
   * Description: 查询所有订单历史
   * </p>
   * 
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 订单历史列表
   */
  List<OrderHistory> queryAll(@Param("offset") int offset, @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: queryAllByUpdateUserName
   * </p>
   * <p>
   * Description: 查询此更新者名的所有订单历史
   * </p>
   * 
   * @param updateUserName 更新者名
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 订单历史列表
   */
  List<OrderHistory> queryAllByUpdateUserName(@Param("updateUserName") String updateUserName,
      @Param("offset") int offset, @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: queryByOrderNum
   * </p>
   * <p>
   * Description: 通过订单编号查询订单历史
   * </p>
   * 
   * @param orderNum 订单编号
   * @return 订单历史对象
   */
  OrderHistory queryByOrderNum(@Param("orderNum") String orderNum);

  /**
   * 
   * <p>
   * Title: queryById
   * </p>
   * <p>
   * Description: 通过id查询订单历史
   * </p>
   * 
   * @param id 序号
   * @return 订单历史对象
   */
  OrderHistory queryById(@Param("id") long id);

  /**
   * 
   * <p>
   * Title: addOrderHistory
   * </p>
   * <p>
   * Description: 增加一条订单历史
   * </p>
   * 
   * @param orderHistory 订单历史对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int addOrderHistory(@Param("orderHistory") OrderHistory orderHistory);

  /**
   * 
   * <p>
   * Title: updateOrderHistory
   * </p>
   * <p>
   * Description: 更新一条订单历史
   * </p>
   * 
   * @param orderHistory 订单历史对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int updateOrderHistory(@Param("orderHistory") OrderHistory orderHistory);

  /**
   * 
   * <p>
   * Title: deleteOrderHistory
   * </p>
   * <p>
   * Description: 删除一条订单历史
   * </p>
   * 
   * @param orderHistory 订单历史对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteOrderHistory(@Param("orderHistory") OrderHistory orderHistory);

  /**
   * 
   * <p>
   * Title: deleteByOrderNum
   * </p>
   * <p>
   * Description: 根据订单编号删除订单历史
   * </p>
   * 
   * @param orderNum 订单编号
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteByOrderNum(@Param("orderNum") String orderNum);

  /**
   * 
   * <p>
   * Title: fuzzyQueryAllByText
   * </p>
   * <p>
   * Description: 模糊查询所有产品
   * </p>
   * 
   * @param searchText 关键词
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 产品列表
   */
  List<OrderHistory> fuzzyQueryAllByText(@Param("searchText") String searchText,
      @Param("offset") int offset, @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: fuzzyCount
   * </p>
   * <p>
   * Description: 获取模糊查询的个数
   * </p>
   * 
   * @param searchText 搜索关键词
   * @return 个数
   */
  int fuzzyCount(@Param("searchText") String searchText);
}
