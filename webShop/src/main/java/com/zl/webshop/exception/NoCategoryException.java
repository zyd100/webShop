/** 
　 * <p>Title: NoCategoryException.java</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月23日 </p>
　 * @version 1.0 
*/
package com.zl.webshop.exception;

/** 
　 * <p>Title: NoCategoryException</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月23日 </p>
*/
public class NoCategoryException extends RuntimeException{
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  public NoCategoryException(String message) {
    super(message);
  }

  public NoCategoryException(String message, Throwable cause) {
    super(message, cause);
  }
}
