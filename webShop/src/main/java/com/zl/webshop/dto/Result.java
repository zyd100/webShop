package com.zl.webshop.dto;



/**
 * 
 * <p>
 * Title: Result
 * </p>
 * <p>
 * Description:封装json对象，用于返回结果
 * </p>
 * 
 * @author 李奕锋
 */
public class Result<T> {

  /**
   * 是否成功标志
   */
  private boolean success;

  /**
   * 成功时返回的数据
   */
  private T data;

  /**
   * 错误信息
   */
  private String error;

  public Result() {}

  /**
   * 
   * <p>
   * Title:Result
   * </p>
   * <p>
   * Description:成功时的构造器
   * </p>
   * 
   * @param success 是否成功标志
   * @param data 成功时返回的数据
   */
  public Result(boolean success, T data) {
    this.success = success;
    this.data = data;
  }

  /**
   * 
   * <p>
   * Title: Result
   * </p>
   * <p>
   * Description: 错误时的构造器
   * </p>
   * 
   * @param success 是否成功标志
   * @param error 错误信息
   */
  public Result(boolean success, String error) {
    this.success = success;
    this.error = error;
  }

  /**
   * @return the success
   */
  public boolean isSuccess() {
    return success;
  }

  /**
   * @param success the success to set
   */
  public void setSuccess(boolean success) {
    this.success = success;
  }

  /**
   * @return the data
   */
  public T getData() {
    return data;
  }

  /**
   * @param data the data to set
   */
  public void setData(T data) {
    this.data = data;
  }

  /**
   * @return the error
   */
  public String getError() {
    return error;
  }

  /**
   * @param error the error to set
   */
  public void setError(String error) {
    this.error = error;
  }


}
