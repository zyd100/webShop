/**
 * <p>
 * Title: ContactStatusEnum.java
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
 * Title: ContactStatusEnum
 * </p>
 * <p>
 * Description: 联系信息枚举
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月15日
 * </p>
 */
public enum ContactStatusEnum {
  /**
   * 默认地址
   */
  DEFAULT(0, "默认地址"),
  /**
   * 其他地址
   */
  OTHER(1, "其他地址");

  /**
   * 索引
   */
  private int state;
  /**
   * 枚举信息
   */
  private String stateInfo;

  private ContactStatusEnum(int state, String stateInfo) {
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
   * 
   * @param index 索引 
   * @return 订单状态枚举
   */
  public static ContactStatusEnum stateOf(int index) {
    for (ContactStatusEnum state : values()) {
      if (state.getState() == index) {
        return state;
      }
    }
    return null;
  }
  public static ContactStatusEnum stateOf(String index) {
    for (ContactStatusEnum state : values()) {
      if (state.getStateInfo().equals(index)) {
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
