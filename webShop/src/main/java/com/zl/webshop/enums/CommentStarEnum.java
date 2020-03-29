/**
 * <p>
 * Title: CommentStarEnum.java
 * </p>
 * <p>
 * Description:
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月29日
 * </p>
 * @version 1.0
 */
package com.zl.webshop.enums;

/**
 * <p>
 * Title: CommentStarEnum
 * </p>
 * <p>
 * Description: 评论推荐星级枚举类
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月29日
 * </p>
 */
public enum CommentStarEnum {
  /**
   * 一星
   */
  ONE(1, "一星"),
  /**
   * 二星
   */
  TWO(2, "二星"),
  /**
   * 三星
   */
  THREE(3, "三星"),
  /**
   * 四星
   */
  FOUR(4, "四星"),
  /**
   * 五星
   */
  FIVE(5, "五星");

  /**
   * 索引
   */
  private int state;
  /**
   * 枚举信息
   */
  private String stateInfo;

  private CommentStarEnum(int state, String stateInfo) {
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
  public static CommentStarEnum stateOf(int index) {
    for (CommentStarEnum state : values()) {
      if (state.getState() == index) {
        return state;
      }
    }
    return null;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getStateInfo() {
    return stateInfo;
  }

  public void setStateInfo(String stateInfo) {
    this.stateInfo = stateInfo;
  }

}
