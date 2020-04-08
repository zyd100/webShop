/**
 * <p>
 * Title: AdminController.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年4月2日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.zl.webshop.dto.EnumDto;
import com.zl.webshop.dto.Result;
import com.zl.webshop.entity.User;
import com.zl.webshop.enums.CommentAuditEnum;
import com.zl.webshop.enums.OrderStatusEnum;
import com.zl.webshop.service.AdminService;
import cn.hutool.core.util.ObjectUtil;

/**
 * <p>
 * Title: AdminController
 * </p>
 * <p>
 * Description: 后台基础控制器 管理登录，登出，跳转主页事件
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年4月2日
 *         </p>
 */
@Controller
public class AdminController {
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private AdminService adminService;
  private static final String ADMINUSERNAME = "adminUserName";

  /**
   * 
   * <p>
   * Title: getAdminPage
   * </p>
   * <p>
   * Description: 前往登录页或主页
   * </p>
   * 
   * @param request 获取登录信息
   * @return 登录页或主页
   */
  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  private String getAdminPage(HttpServletRequest request) {
    if (ObjectUtil.isNull(request.getSession().getAttribute(ADMINUSERNAME))) {
      // 没登录，跳转至登录页
      logger.debug("loginpage");
      return "/admitLogin/backLogin";
    }
    // 前往后台主页
    logger.debug("adminPage");
    return "/admitHome/backHome";
  }

  /**
   * 
   * <p>
   * Title: login
   * </p>
   * <p>
   * Description: 表单登录事件
   * </p>
   * 
   * @param userName 用户名
   * @param password 密码
   * @param request 存数据
   * @return 主页或登录页
   */
  @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
  private String login(@RequestParam("userName") String userName,
      @RequestParam("password") String password, HttpServletRequest request) {
    User admin = new User();
    admin.setPassword(password);
    admin.setUserName(userName);
    try {
      boolean flag = adminService.login(admin);
      if (flag == true) {
        // 登录成功
        request.getSession().setAttribute(ADMINUSERNAME, userName);

      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    }

    // 重定向至主页或登录页
    return "redirect: /webShop/admin";
  }

  /**
   * 
   * <p>
   * Title: loginoff
   * </p>
   * <p>
   * Description: 退出登录事件
   * </p>
   * 
   * @param request 清除登录信息
   * @return 登录页
   */
  @RequestMapping(value = "/admin/logoff", method = RequestMethod.GET)
  private String loginoff(HttpServletRequest request) {
    request.getSession().invalidate();
    // 重定向至登录页
    return "redirect: /webShop/admin";
  }

  /**
   * 
   * <p>
   * Title: getAuditEnum
   * </p>
   * <p>
   * Description: 获取评论审核状态枚举
   * </p>
   * @return 枚举
   */
  @RequestMapping(value = "/admin/enums/commentaudit", method = RequestMethod.GET,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String getAuditEnum() {
    Result<List<EnumDto>> result = null;
    List<EnumDto>enums=new ArrayList<EnumDto>();
    try {
      for (CommentAuditEnum auditEnum : CommentAuditEnum.values()) {
        enums.add(new EnumDto(auditEnum.getStateInfo(), auditEnum.getState()));
      }
      result = new Result<List<EnumDto>>(true, enums);
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: getOrderStatusEnum
   * </p>
   * <p>
   * Description: 获取订单状态枚举
   * </p>
   * @return 枚举
   */
  @RequestMapping(value = "/admin/enums/orderstatus", method = RequestMethod.GET,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String getOrderStatusEnum() {
    Result<List<EnumDto>> result = null;
    List<EnumDto>enums=new ArrayList<EnumDto>();
    try {
      for (OrderStatusEnum statusEnum : OrderStatusEnum.values()) {
      enums.add(new EnumDto(statusEnum.getStateInfo(), statusEnum.getState()));
      }
      result = new Result<List<EnumDto>>(true, enums);
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }
}
