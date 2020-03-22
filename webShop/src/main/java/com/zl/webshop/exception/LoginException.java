/**
 * <p>
 * Title: LoginException.java
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
 * Title: LoginException
 * </p>
 * <p>
 * Description: 用户登录异常
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月15日
 * </p>
 */
public class LoginException extends RuntimeException {

  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  public LoginException(String message) {
    super(message);
  }

  public LoginException(String message, Throwable cause) {
    super(message, cause);
  }
}
