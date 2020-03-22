/**
 * <p>
 * Title: OrderHistory.java
 * </p>
 * <p>
 * Description:
 * </p>
 * @author zyd @date 2020年3月11日 @version 1.0
 */
package com.zl.webshop.entity;

import java.time.LocalDateTime;

/**
 * <p>
 * Title: OrderHistory
 * </p>
 * <p>
 * Description: 订单历史表实体类
 * </p>
 * @author zyd @date 2020年3月11日
 */
public class OrderHistory {
  /**
   * 序号
   */
  private long id;
  /**
   * 订单编号
   */
  private String orderNum;
  /**
   * 更新者用户名
   */
  private String updateUserName;
  /**
   * 订单状态
   */
  private int status;
  /**
   * 创建时间
   */
  private LocalDateTime createTime;

  /**
   * @return the id 序号
   */
  public long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * @return the orderNum 订单编号
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
   * @return the updateUserName 更新者用户名
   */
  public String getUpdateUserName() {
    return updateUserName;
  }

  /**
   * @param updateUserName the updateUserName to set
   */
  public void setUpdateUserName(String updateUserName) {
    this.updateUserName = updateUserName;
  }

  /**
   * @return the status 订单状态
   */
  public int getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(int status) {
    this.status = status;
  }

  /**
   * @return the createTime 创建时间
   */
  public LocalDateTime getCreateTime() {
    return createTime;
  }

  /**
   * @param createTime the createTime to set
   */
  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }


  @Override
  public String toString() {
    return "OrderHistory [id=" + id + ", orderNum=" + orderNum + ", updateUserName="
        + updateUserName + ", status=" + status + ", createTime=" + createTime + "]";
  }

}
