package com.zl.webshop.exception;
/**
 * 
　 * <p>Title: NoCartException</p> 
　 * <p>Description: 获取购物车内容失败</p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月21日 </p>
 */
public class NoCartException extends RuntimeException{
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  public NoCartException(String message) {
    super(message);
  }

  public NoCartException(String message, Throwable cause) {
    super(message, cause);
  }
}
