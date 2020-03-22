/**
 * <p>
 * Title: WrongUserNamePwdException.java
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
 * Title: WrongUserNamePwdException
 * </p>
 * <p>
 * Description: 用户名或密码错误 异常
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月15日
 * </p>
 */
public class WrongUserNamePwdException extends RuntimeException {

  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  public WrongUserNamePwdException(String message) {
    super(message);
  }

  public WrongUserNamePwdException(String message, Throwable cause) {
    super(message, cause);
  }
}
