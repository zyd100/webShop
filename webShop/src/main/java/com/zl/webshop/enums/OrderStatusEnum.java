/**
 * <p>
 * Title: OrderStatusEnum.java
 * </p>
 * <p>
 * Description:
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月15日
 * </p>
 * @version 1.0
 */
package com.zl.webshop.enums;

/**
 * <p>
 * Title: OrderStatusEnum
 * </p>
 * <p>
 * Description: 订单状态枚举
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月15日
 * </p>
 */
public enum OrderStatusEnum {
  /**
   * 已付款
   */
  ORDER_PAYED(0, "已付款"),
  /**
   * 已下单
   */
  ORDER_PLACED(1, "已下单"),
  /**
   * 已配送
   */
  ORDER_SHIPED(2, "已配送"),
  /**
   * 已寄到
   */
  ORDER_DONE(3, "已寄到"),
  /**
   * 购物车
   */
  SHOPPING_CART(4,"购物车"),
  /**
   * 收藏夹
   */
  STAR(5,"收藏夹");
  /**
   * 索引
   */
  private int state;
  /**
   * 枚举信息
   */
  private String stateInfo;

  private OrderStatusEnum(int state, String stateInfo) {
    setState(state);
    setStateInfo(stateInfo);
  }

  /**
   * 
   * <p>
   * Title: stateOf
   * </p>
   * <p>
   * Description:根据索引获取枚举
   * </p>
   * @param index 索引
   *  @return 订单状态枚举
   */
  public static OrderStatusEnum stateOf(int index) {
    for (OrderStatusEnum state : values()) {
      if (state.getState() == index) {
        return state;
      }
    }
    return null;
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

}
