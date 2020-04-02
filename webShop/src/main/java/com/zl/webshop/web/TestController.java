/**
 * <p>
 * Title: HomeController.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月23日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.web;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.zl.webshop.dto.ProductExecution;
import com.zl.webshop.dto.Result;
import com.zl.webshop.entity.Product;
import com.zl.webshop.entity.ProductCategory;
import com.zl.webshop.exception.NoProductException;
import com.zl.webshop.service.ProductService;
import cn.hutool.core.util.RandomUtil;

/**
 * <p>
 * Title: TestController
 * </p>
 * <p>
 * Description: 测试例控制器 用于测试前后端交互的函数
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月23日
 *         </p>
 */
@Controller
public class TestController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private ProductService productService;

  
   
  /**
   * 
   * <p>
   * Title: showHomePage
   * </p>
   * <p>
   * Description: 拦截前往主页的请求，并返回主页需要的数据
   * </p>
   * 
   * @param model 存放数据的地方
   * @return "categories" 所有类别 "rollProducts"滚动推荐的产品"randomProducts"随机的商品展示8组，1组4个
   */
  @RequestMapping(value = "/homePage", method = RequestMethod.GET)
  private String showHomePage(Model model) {
    // 获取产品类别
    List<ProductCategory> categories = productService.getCategories();
    // 获取首页滚动以及推荐
    List<Product> rollProducts = null;
    try {
      rollProducts = productService.getProductsRandom(7).getProductList();
    } catch (Exception e) {
      logger.error(e.getMessage());
      // 失败返回空数组
      rollProducts = new ArrayList<Product>();
    }
    ProductExecution productExecution = null;
    // 获取随机展示商品
    List<ProductExecution> randomProducts = new ArrayList<ProductExecution>();
    // 随机8个种类 再从各个种类的类别里随机出4个商品
    int categoriesCount=8;
    for (ProductCategory category : RandomUtil.randomEles(categories, categoriesCount)) {
      try {
        productExecution = productService.getProductsRandom(category.getId(), 4);
        randomProducts.add(productExecution);
      } catch (NoProductException e) {
        logger.error(e.getMessage());
        continue;
      } catch (Exception e) {
        logger.error(e.getMessage());
        continue;
      }
    }
    // 放入model
    model.addAttribute("categories", JSON.toJSONString(categories));
    model.addAttribute("rollProducts", rollProducts);
    model.addAttribute("randomProducts", randomProducts);
    // WEB-INF/jsp/home/homePage.jsp
    return "/home/homePage";
  }

  /**
   * 
   * <p>
   * Title: searchProduct
   * </p>
   * <p>
   * Description: 前往搜索页面的拦截器
   * </p>
   * 
   * @param searchText 搜索关键词
   * @param model 存放数据的地方
   * @return "products" 保存第一页的搜索结果 默认40个 "searchText" 搜索关键词，便于后续操作
   */
  @RequestMapping(value = "/homePage/search/{searchText}", method = RequestMethod.GET)
  private String searchProduct(@PathVariable("searchText") String searchText, Model model) {
    ProductExecution productExecution = productService.searchByText(searchText, 0, 40);
    model.addAttribute("products", JSON.toJSONString(productExecution));
    model.addAttribute("searchText", searchText);
    // WEB-INF/jsp/home/search.jsp
    return "/home/search";
  }

  /**
   * 
   * <p>
   * Title: searchProduct
   * </p>
   * <p>
   * Description: 搜索页内使用ajax进行操作
   * </p>
   * 
   * @param searchText 搜索关键词
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 搜索结果
   */
  @RequestMapping(value = "/homePage/search", method = RequestMethod.POST,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String searchProduct(@RequestParam("searchText") String searchText,
      @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
    ProductExecution productExecution = null;
    Result<ProductExecution> result = null;
    try {
      productExecution = productService.searchByText(searchText, offset, limit);
      result = new Result<ProductExecution>(true, productExecution);
    } catch (NoProductException e) {
      result = new Result<>(false, e.getMessage());
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }

    String resultJson = JSON.toJSONString(result);
    return resultJson;
  }

  @RequestMapping(value = "/homePage/put", method = RequestMethod.PUT,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String testPut(@RequestBody ProductExecution productExecution) {
    System.out.println("所有更新，返回更新结果");
    System.out.println(JSON.toJSONString(productExecution));
    return JSON.toJSONString(productExecution);
  }

  @RequestMapping(value = "/homePage/put", method = RequestMethod.DELETE,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String testDelete(@RequestBody ProductExecution productExecution) {
    System.out.println("执行删除，返回删除结果");
    System.out.println(JSON.toJSONString(productExecution));
    return JSON.toJSONString(productExecution);
  }

  @RequestMapping(value = "/homePage/put", method = RequestMethod.PATCH,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String testPatch(@RequestBody ProductExecution productExecution) {
    System.out.println("执行部分更新，返回删除结果");
    System.out.println(JSON.toJSONString(productExecution));
    return JSON.toJSONString(productExecution);
  }

  @RequestMapping(value = "/homePage/put", method = RequestMethod.GET)
  private String testGet() {
    return "/home/homePage";
  }
}
