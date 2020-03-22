/** 
　 * <p>Title: ProductLostException.java</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月21日 </p>
　 * @version 1.0 
*/
package com.zl.webshop.exception;

/** 
　 * <p>Title: ProductLostException</p> 
　 * <p>Description: 产品失效异常</p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月21日 </p>
*/
public class ProductLostException extends RuntimeException{
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  public ProductLostException(String message) {
    super(message);
  }

  public ProductLostException(String message, Throwable cause) {
    super(message, cause);
  }
}
