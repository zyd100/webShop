/** 
　 * <p>Title: StarStatusException.java</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月26日 </p>
　 * @version 1.0 
*/
package com.zl.webshop.exception;

/** 
　 * <p>Title: StarStatusException</p> 
　 * <p>Description: 收藏夹状态异常</p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月26日 </p>
*/
public class StarStatusException extends RuntimeException{
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  public StarStatusException(String message) {
    super(message);
  }

  public StarStatusException(String message, Throwable cause) {
    super(message, cause);
  }
}
