/**
 * <p>
 * Title: LoginInterceptor.java
 * </p>
 * <p>
 * Description:
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月23日
 * </p>
 * @version 1.0
 */
package com.zl.webshop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import cn.hutool.core.util.StrUtil;

/**
 * <p>
 * Title: LoginInterceptor
 * </p>
 * <p>
 * Description: 用户登录前端的拦截器
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年3月23日
 * </p>
 */
public class LoginInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    HttpSession session = request.getSession();
    // 尝试获取已登录的用户名
    String loginUserName = (String) session.getAttribute("loginUserName");
    return StrUtil.isNotEmpty(loginUserName);
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    // TODO Auto-generated method stub

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    // TODO Auto-generated method stub

  }

}
