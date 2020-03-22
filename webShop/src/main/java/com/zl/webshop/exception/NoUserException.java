/**
 * <p>
 * Title: NoUserException.java
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
 * Title: NoUserException
 * </p>
 * <p>
 * Description: 没有此用户信息 异常
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月15日
 * </p>
 */
public class NoUserException extends RuntimeException {
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  public NoUserException(String message) {
    super(message);
  }

  public NoUserException(String message, Throwable cause) {
    super(message, cause);
  }
}
