/** 
　 * <p>Title: InfoEmptyException.java</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月21日 </p>
　 * @version 1.0 
*/
package com.zl.webshop.exception;

/** 
　 * <p>Title: InfoEmptyException</p> 
　 * <p>Description: 提交信息为空</p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月21日 </p>
*/
public class InfoEmptyException extends RuntimeException{
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  public InfoEmptyException(String message) {
    super(message);
  }

  public InfoEmptyException(String message, Throwable cause) {
    super(message, cause);
  }
}
