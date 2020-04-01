/** 
　 * <p>Title: CommentAuditEnum.java</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月31日 </p>
　 * @version 1.0 
*/
package com.zl.webshop.enums;

/** 
　 * <p>Title: CommentAuditEnum</p> 
　 * <p>Description: 评论审批枚举</p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月31日 </p>
*/
public enum CommentAuditEnum {
  /**
   * 审批中
   */
  AUDIT(1,"审批中"),
  /**
   * 批准
   */
  RATIFY(2,"批准");
  /**
   * 索引
   */
  private int state;
  /**
   * 枚举信息
   */
  private String stateInfo;

  private CommentAuditEnum(int state, String stateInfo) {
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
  public static CommentAuditEnum stateOf(int index) {
    for (CommentAuditEnum state : values()) {
      if (state.getState() == index) {
        return state;
      }
    }
    return null;
  }

  public static CommentAuditEnum stateOf(String index) {
    for (CommentAuditEnum state : values()) {
      if (state.getStateInfo().equals(index)) {
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
