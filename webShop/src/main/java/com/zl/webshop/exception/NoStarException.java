/** 
　 * <p>Title: NoStarException.java</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月26日 </p>
　 * @version 1.0 
*/
package com.zl.webshop.exception;

/** 
　 * <p>Title: NoStarException</p> 
　 * <p>Description: 收藏夹为空异常</p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月26日 </p>
*/
public class NoStarException extends RuntimeException{
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  public NoStarException(String message) {
    super(message);
  }

  public NoStarException(String message, Throwable cause) {
    super(message, cause);
  }
}
