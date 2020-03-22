/** 
　 * <p>Title: NoProductException.java</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月21日 </p>
　 * @version 1.0 
*/
package com.zl.webshop.exception;

/** 
　 * <p>Title: NoProductException</p> 
　 * <p>Description: 没有相关产品异常</p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月21日 </p>
*/
public class NoProductException extends RuntimeException{
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  public NoProductException(String message) {
    super(message);
  }

  public NoProductException(String message, Throwable cause) {
    super(message, cause);
  }
}
