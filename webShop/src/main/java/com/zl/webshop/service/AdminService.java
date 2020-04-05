/**
 * <p>
 * Title: AdminService.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月19日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.service;


import com.zl.webshop.entity.User;


/**
 * <p>
 * Title: AdminService
 * </p>
 * <p>
 * Description: 管理员业务接口
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月19日
 *         </p>
 */
public interface AdminService {
  /**
   * 
   * <p>
   * Title: login
   * </p>
   * <p>
   * Description: 管理员登录
   * </p>
   * 
   * @param admin 管理员
   * @return 登录成功返回true
   */
  boolean login(User admin);

  /**
   * 
   * <p>
   * Title: register
   * </p>
   * <p>
   * Description: 管理员注册
   * </p>
   * 
   * @param register 注册对象
   * @return 注册结果
   */
  boolean register(User register);
}
