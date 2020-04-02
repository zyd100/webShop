/**
 * <p>
 * Title: AdminUserController.java
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

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.zl.webshop.dto.Result;
import com.zl.webshop.dto.UserExecution;
import com.zl.webshop.entity.User;
import com.zl.webshop.service.AdminUserService;

/**
 * <p>
 * Title: AdminUserController
 * </p>
 * <p>
 * Description: 后台管理用户控制器 负责产品的删改查事件
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年4月2日
 *         </p>
 */
@Controller
@RequestMapping(value = "/admin/users")
public class AdminUserController {
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private AdminUserService adminUserService;

  /**
   * 
   * <p>
   * Title: getUsers
   * </p>
   * <p>
   * Description: 获取用户
   * </p>
   * 
   * @param searchText 搜索关键词 可不填
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 查询结果
   */
  @RequestMapping(value = "", method = RequestMethod.GET,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String getUsers(@RequestParam(value = "searchText",required = false) String searchText,
      @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
    Result<List<UserExecution>> result = null;
    try {
      result = new Result<List<UserExecution>>(true,
          adminUserService.getUsers(searchText, offset, limit));
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: deleteUser
   * </p>
   * <p>
   * Description: 删除用户
   * </p>
   * 
   * @param user 用户
   * @return 删除结果
   */
  @RequestMapping(value = "", method = RequestMethod.DELETE,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String deleteUser(@RequestBody User user) {
    Result<Integer> result = null;
    try {
      result = new Result<Integer>(true, adminUserService.deleteUser(user));
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: updateUser
   * </p>
   * <p>
   * Description: 更新用户基本信息
   * </p>
   * 
   * @param user 用户
   * @return 更新结果
   */
  @RequestMapping(value = "", method = RequestMethod.PUT,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String updateUser(@RequestBody User user) {
    Result<UserExecution> result = null;
    try {
      
      result = new Result<UserExecution>(true, adminUserService.updateUser(user));
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }
}
