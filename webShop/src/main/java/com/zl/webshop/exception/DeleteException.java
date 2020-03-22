/**
 * <p>
 * Title: DeleteException.java
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
 * Title: DeleteException
 * </p>
 * <p>
 * Description: 删除信息异常
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月18日
 * </p>
 */
public class DeleteException extends RuntimeException {
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  public DeleteException(String message) {
    super(message);
  }

  public DeleteException(String message, Throwable cause) {
    super(message, cause);
  }
}
