/**
 * <p>
 * Title: AdminOrderController.java
 * </p>
 * <p>
 * Description:
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年4月2日
 * </p>
 * @version 1.0
 */
package com.zl.webshop.web;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.zl.webshop.dto.OrderExecution;
import com.zl.webshop.dto.Result;
import com.zl.webshop.enums.OrderStatusEnum;
import com.zl.webshop.service.AdminOrderService;

/**
 * <p>
 * Title: AdminOrderController
 * </p>
 * <p>
 * Description: 后台订单管理控制器 负责订单删改查操作
 * </p>
 * @author zyd
 * <p>
 * 创建日期：2020年4月2日
 * </p>
 */
@Controller
@RequestMapping(value = "/admin/orders")
public class AdminOrderController {
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private AdminOrderService adminOrderService;

  /**
   * 
   * <p>
   * Title: getOrders
   * </p>
   * <p>
   * Description: 获取订单列表
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
  private String getOrders(@RequestParam(value = "searchText",required = false) String searchText,
      @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
    Result<List<OrderExecution>> result = null;
    try {
      result = new Result<List<OrderExecution>>(true,
          adminOrderService.getOrderInfos(searchText, offset, limit));
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: deleteOrders
   * </p>
   * <p>
   * Description: 删除订单
   * </p>
   * 
   * @param orderNum 订单编号
   * @return 删除结果
   */
  @RequestMapping(value = "", method = RequestMethod.DELETE,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String deleteOrders(String orderNum) {
    Result<Integer> result = null;
    try {
      result = new Result<Integer>(true, adminOrderService.deleteOrderHistory(orderNum));
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: updateOrdersStatus
   * </p>
   * <p>
   * Description: 更新分类基本信息
   * </p>
   * 
   * @param orderNum 订单编号
   * @param status 订单状态
   * @return 更新结果
   */
  @RequestMapping(value = "", method = RequestMethod.PUT,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String updateOrdersStatus(@RequestParam("orderNum") String orderNum,
      @RequestParam("status") int status) {
    Result<OrderExecution> result = null;
    try {
      result = new Result<OrderExecution>(true,
          adminOrderService.updateOrderStatus(OrderStatusEnum.stateOf(status), orderNum));
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }
}
