/**
 * <p>
 * Title: AdminProductController.java
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
import com.zl.webshop.dto.ProductExecution;
import com.zl.webshop.dto.Result;
import com.zl.webshop.entity.Product;
import com.zl.webshop.service.AdminProductService;

/**
 * <p>
 * Title: AdminProductController
 * </p>
 * <p>
 * Description:后台管理产品控制器 负责产品的增删改查事件
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年4月2日
 *         </p>
 */
@Controller
@RequestMapping(value = "/admin/products")
public class AdminProductController {
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private AdminProductService adminProductService;

  /**
   * 
   * <p>
   * Title: getProducts
   * </p>
   * <p>
   * Description: 获取商品
   * </p>
   * 
   * @param searchText 搜索关键词 可以null
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 查询结果
   */
  @RequestMapping(value = "", method = RequestMethod.GET,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String getProducts(@RequestParam(value = "searchText",required = false) String searchText,
      @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
    Result<List<ProductExecution>> result = null;
    try {
      result = new Result<List<ProductExecution>>(true,
          adminProductService.getProducts(searchText, offset, limit));
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: deleteProducts
   * </p>
   * <p>
   * Description: 删除一个产品
   * </p>
   * 
   * @param product 产品
   * @return 删除成功返回最新产品数
   */
  @RequestMapping(value = "", method = RequestMethod.DELETE,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String deleteProducts(@RequestBody Product product) {
    Result<ProductExecution> result = null;
    try {
      result = new Result<ProductExecution>(true, adminProductService.deleteProduct(product));
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }
}
