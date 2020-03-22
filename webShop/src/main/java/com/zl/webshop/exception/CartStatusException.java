/** 
　 * <p>Title: CartStatusException.java</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月21日 </p>
　 * @version 1.0 
*/
package com.zl.webshop.exception;

/** 
　 * <p>Title: CartStatusException</p> 
　 * <p>Description: 购物车订单状态异常</p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月21日 </p>
*/
public class CartStatusException extends RuntimeException{
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  public CartStatusException(String message) {
    super(message);
  }

  public CartStatusException(String message, Throwable cause) {
    super(message, cause);
  }
}
