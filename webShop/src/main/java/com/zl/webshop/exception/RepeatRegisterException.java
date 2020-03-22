/**
 * <p>
 * Title: RepeatRegisterException.java
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
 * Title: RepeatRegisterException
 * </p>
 * <p>
 * Description: 重复注册异常
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月15日
 * </p>
 */
public class RepeatRegisterException extends RuntimeException {
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  public RepeatRegisterException(String message) {
    super(message);
  }

  public RepeatRegisterException(String message, Throwable cause) {
    super(message, cause);
  }
}
