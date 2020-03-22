package com.zl.webshop.exception;

/**
 * 
 * <p>
 * Title: AddToCartException
 * </p>
 * <p>
 * Description: 添加购物车 异常
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月21日
 * </p>
 */
public class AddToCartException extends RuntimeException {
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  public AddToCartException(String message) {
    super(message);
  }

  public AddToCartException(String message, Throwable cause) {
    super(message, cause);
  }
}
