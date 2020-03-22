/**
 * <p>
 * Title: RegisterException.java
 * </p>
 * <p>
 * Description:
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月15日
 * </p>
 * @version 1.0
 */
package com.zl.webshop.exception;

/**
 * <p>
 * Title: RegisterException
 * </p>
 * <p>
 * Description: 用户注册异常
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月15日
 * </p>
 */
public class RegisterException extends RuntimeException {
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  public RegisterException(String message) {
    super(message);
  }

  public RegisterException(String message, Throwable cause) {
    super(message, cause);
  }
}
