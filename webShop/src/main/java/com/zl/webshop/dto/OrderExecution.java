/**
 * <p>
 * Title: OrderExecution.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月15日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.dto;

import java.util.List;
import com.zl.webshop.entity.OrderInfo;
import com.zl.webshop.entity.OrderItem;
import com.zl.webshop.entity.Product;
import com.zl.webshop.enums.OrderStatusEnum;

/**
 * <p>
 * Title: OrderExecution
 * </p>
 * <p>
 * Description: 保存了订单信息，相关用户，以及订单条目列表
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月15日
 *         </p>
 */
public class OrderExecution {
  /**
   * 订单编号
   */
  private String orderNum;
  /**
   * 用户名
   */
  private String userName;
  /**
   * 订单状态信息
   */
  private String stateInfo;
  /**
   * 订单状态枚举索引
   */
  private int state;
  /**
   * 订单条目列表
   */
  private List<OrderItem> orderItemList;
  private List<Product> productList;
  /**
   * 订单信息
   */
  private OrderInfo orderInfo;
  /**
   * 订单列表
   */
  private List<OrderInfo> orderInfos;

  /**
   * 
   * <p>
   * Title:OrderExecution 获取订单失败的构造器
   * </p>
   * <p>
   * Description:获取订单信息失败，仅保存订单编号和用户名
   * </p>
   * 
   * @param orderNum 订单编号
   * @param userName 用户名
   */
  public OrderExecution(String orderNum, String userName) {
    setOrderNum(orderNum);
    setUserName(userName);
  }

  /**
   * 
   * <p>
   * Title:
   * </p>
   * <p>
   * Description: 订单信息的构造器
   * </p>
   * 
   * @param orderInfo 订单信息
   * @param orderStatusEnum 订单状态
   */
  public OrderExecution(OrderInfo orderInfo, OrderStatusEnum orderStatusEnum) {
    setOrderInfo(orderInfo);
    setOrderNum(orderInfo.getOrderNum());
    setUserName(orderInfo.getUserName());
    setState(orderStatusEnum.getState());
    setStateInfo(orderStatusEnum.getStateInfo());
  }

  /**
   * 
   * <p>
   * Title: OrderExecution 获取订单成功的构造器
   * </p>
   * <p>
   * Description: 获取订单信息成功，保存订单所有相关信息
   * </p>
   * 
   * @param orderInfo 订单信息
   * @param orderItems 订单条目列表
   * @param orderStatusEnum 订单状态
   * @param products 条目对应的产品信息
   */
  public OrderExecution(OrderInfo orderInfo, List<OrderItem> orderItems, List<Product> products,
      OrderStatusEnum orderStatusEnum) {
    setOrderInfo(orderInfo);
    setOrderItemList(orderItems);
    setProductList(products);
    setOrderNum(orderInfo.getOrderNum());
    setUserName(orderInfo.getUserName());
    setState(orderStatusEnum.getState());
    setStateInfo(orderStatusEnum.getStateInfo());
  }

  /**
   * 
   * <p>
   * Title: 获取订单成功的构造器2
   * </p>
   * <p>
   * Description: 保存订单信息以及订单条目
   * </p>
   * 
   * @param orderInfo 订单信息
   * @param orderItems 订单条目列表
   * @param orderStatusEnum 订单状态
   */
  public OrderExecution(OrderInfo orderInfo, List<OrderItem> orderItems,
      OrderStatusEnum orderStatusEnum) {
    setOrderInfo(orderInfo);
    setOrderItemList(orderItems);
    setOrderNum(orderInfo.getOrderNum());
    setUserName(orderInfo.getUserName());
    setState(orderStatusEnum.getState());
    setStateInfo(orderStatusEnum.getStateInfo());
  }

  /**
   * 
   * <p>
   * Title:
   * </p>
   * <p>
   * Description: 订单条目的构造器
   * </p>
   * 
   * @param userName 用户名
   * @param orderNum 订单编号
   * @param orderItems 订单条目列表
   */
  public OrderExecution(String userName, String orderNum, List<OrderItem> orderItems) {
    setUserName(userName);
    setOrderNum(orderNum);
    setOrderItemList(orderItems);
  }

  /**
   * 
   * <p>
   * Title: 默认构造器
   * </p>
   * <p>
   * Description: 默认构造器
   * </p>
   */
  public OrderExecution() {}

  /**
   * @return the orderNum
   */
  public String getOrderNum() {
    return orderNum;
  }

  /**
   * @param orderNum the orderNum to set
   */
  public void setOrderNum(String orderNum) {
    this.orderNum = orderNum;
  }

  /**
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * @return the stateInfo
   */
  public String getStateInfo() {
    return stateInfo;
  }

  /**
   * @param stateInfo the stateInfo to set
   */
  public void setStateInfo(String stateInfo) {
    this.stateInfo = stateInfo;
  }

  /**
   * @return the state
   */
  public int getState() {
    return state;
  }

  /**
   * @param state the state to set
   */
  public void setState(int state) {
    this.state = state;
  }

  /**
   * @return the orderItemList
   */
  public List<OrderItem> getOrderItemList() {
    return orderItemList;
  }

  /**
   * @param orderItemList the orderItemList to set
   */
  public void setOrderItemList(List<OrderItem> orderItemList) {
    this.orderItemList = orderItemList;
  }

  /**
   * @return the orderInfo
   */
  public OrderInfo getOrderInfo() {
    return orderInfo;
  }

  /**
   * @param orderInfo the orderInfo to set
   */
  public void setOrderInfo(OrderInfo orderInfo) {
    this.orderInfo = orderInfo;
  }

  /**
   * @return the productList
   */
  public List<Product> getProductList() {
    return productList;
  }

  /**
   * @param productList the productList to set
   */
  public void setProductList(List<Product> productList) {
    this.productList = productList;
  }


  /**
   * @return the orderInfos
   */
  public List<OrderInfo> getOrderInfos() {
    return orderInfos;
  }

  /**
   * @param orderInfos the orderInfos to set
   */
  public void setOrderInfos(List<OrderInfo> orderInfos) {
    this.orderInfos = orderInfos;
  }

  @Override
  public String toString() {
    return "OrderExecution [orderNum=" + orderNum + ", userName=" + userName + ", stateInfo="
        + stateInfo + ", state=" + state + ", orderItemList=" + orderItemList + ", productList="
        + productList + ", orderInfo=" + orderInfo + "]";
  }



}
