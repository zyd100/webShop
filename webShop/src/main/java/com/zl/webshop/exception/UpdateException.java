/**
 * <p>
 * Title: UpdateException.java
 * </p>
 * <p>
 * Description:
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月18日
 * </p>
 * @version 1.0
 */
package com.zl.webshop.exception;

/**
 * <p>
 * Title: UpdateException
 * </p>
 * <p>
 * Description: 更新信息异常
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月18日
 * </p>
 */
public class UpdateException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public UpdateException(String message) {
    super(message);
  }

  public UpdateException(String message, Throwable cause) {
    super(message, cause);
  }
}
