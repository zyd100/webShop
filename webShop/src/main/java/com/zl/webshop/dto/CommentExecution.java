/**
 * <p>
 * Title: CommentExecution.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月31日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.dto;

import com.zl.webshop.entity.Comment;
import com.zl.webshop.enums.CommentAuditEnum;
import com.zl.webshop.enums.CommentStarEnum;

/**
 * <p>
 * Title: CommentExecution
 * </p>
 * <p>
 * Description: 评论表dto
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月31日
 *         </p>
 */
public class CommentExecution {

  /**
   * 评论表
   */
  private Comment comment;
  /**
   * 评论审核状态
   */
  private int state;
  /**
   * 评论审核状态的信息
   */
  private String stateInfo;
  /**
   * 好评度
   */
  private int starState;
  /**
   * 好评度信息
   */
  private String starStateInfo;

  public CommentExecution() {}

  /**
   * 
   * <p>
   * Title: 构造器
   * </p>
   * <p>
   * Description: 构造器
   * </p>
   * @param comment 评论表对象 
   * @param auditEnum 评论状态枚举
   * @param starEnum 评论好评度枚举
   */
  public CommentExecution(Comment comment, CommentAuditEnum auditEnum, CommentStarEnum starEnum) {
    setComment(comment);
    setState(auditEnum.getState());
    setStateInfo(auditEnum.getStateInfo());
    setStarState(starEnum.getState());
    setStarStateInfo(starEnum.getStateInfo());
  }

  public int getStarState() {
    return starState;
  }

  public void setStarState(int starState) {
    this.starState = starState;
  }

  public String getStarStateInfo() {
    return starStateInfo;
  }

  public void setStarStateInfo(String starStateInfo) {
    this.starStateInfo = starStateInfo;
  }

  public Comment getComment() {
    return comment;
  }

  public void setComment(Comment comment) {
    this.comment = comment;
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

  @Override
  public String toString() {
    return "CommentExecution [comment=" + comment + ", state=" + state + ", stateInfo=" + stateInfo
        + ", starState=" + starState + ", starStateInfo=" + starStateInfo + "]";
  }

}
