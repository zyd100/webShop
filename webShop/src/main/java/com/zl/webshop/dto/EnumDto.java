/**
 * <p>
 * Title: EnumDto.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年4月8日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.dto;

/**
 * <p>
 * Title: EnumDto
 * </p>
 * <p>
 * Description: 枚举dto
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年4月8日
 *         </p>
 */
public class EnumDto {
  private String stateInfo;
  private int state;

  public String getStateInfo() {
    return stateInfo;
  }

  public EnumDto() {

  }

  public EnumDto(String stateInfo, int state) {
    setState(state);
    setStateInfo(stateInfo);
  }

  public void setStateInfo(String stateInfo) {
    this.stateInfo = stateInfo;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  @Override
  public String toString() {
    return "EnumDto [stateInfo=" + stateInfo + ", state=" + state + "]";
  }

}
