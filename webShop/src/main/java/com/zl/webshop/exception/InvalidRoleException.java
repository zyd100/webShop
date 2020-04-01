package com.zl.webshop.exception;

/**
 * 
 * <p>
 * Title: InvalidRoleException
 * </p>
 * <p>
 * Description: 非法权限异常
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月30日
 * </p>
 */
public class InvalidRoleException extends RuntimeException {
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  public InvalidRoleException(String message) {
    super(message);
  }

  public InvalidRoleException(String message, Throwable cause) {
    super(message, cause);
  }
}
