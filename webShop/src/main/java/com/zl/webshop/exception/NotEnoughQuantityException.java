/** 
　 * <p>Title: NotEnoughQuantityException.java</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月22日 </p>
　 * @version 1.0 
*/
package com.zl.webshop.exception;

/** 
　 * <p>Title: NotEnoughQuantityException</p> 
　 * <p>Description: 购买数量超出产品库存异常</p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月22日 </p>
*/
public class NotEnoughQuantityException extends RuntimeException{
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  public NotEnoughQuantityException(String message) {
    super(message);
  }

  public NotEnoughQuantityException(String message, Throwable cause) {
    super(message, cause);
  }
}
