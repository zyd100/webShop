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
 *         创建日期：2020年3月25日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
import com.zl.webshop.dto.CommentExecution;
import com.zl.webshop.dto.ProductExecution;
import com.zl.webshop.dto.Result;
import com.zl.webshop.entity.Comment;
import com.zl.webshop.entity.Product;
import com.zl.webshop.entity.ProductCategory;
import com.zl.webshop.enums.CommentAuditEnum;
import com.zl.webshop.exception.NoCategoryException;
import com.zl.webshop.exception.NoProductException;
import com.zl.webshop.service.CommentService;
import com.zl.webshop.service.ProductService;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

/**
 * <p>
 * Title: HomeController
 * </p>
 * <p>
 * Description: 主页、搜索页、详情页的控制器
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月25日
 *         </p>
 */
@Controller
public class HomeController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  /**
   * 首页类别展示数
   */
  public static final int CATEGORIERCOUNT = 39;
  /**
   * 首页产品滚动推荐数
   */
  public static final int ROLLPRODUCTSCOUNT = 9;
  /**
   * 随机商品展示类别数
   */
  public static final int RANDOMSHOWPRODUCTSGROUPCOUNT = 8;
  /**
   * 随机商品展示数
   */
  public static final int RANDOMSHOWPRODUCTSCOUNT = 4;
  /**
   * 默认查询起始位置
   */
  public static final int DEFAULTOFFSET = 0;
  /**
   * 搜索页默认查询条数
   */
  public static final int DEFAULTSEARCHLIMIT = 40;
  @Autowired
  private ProductService productService;
  @Autowired
  private CommentService commentService;

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  private String getHomePage(Model model) {
    // 获取产品类别
    List<ProductCategory> categories =
        productService.getCategories().stream().limit(CATEGORIERCOUNT).collect(Collectors.toList());
    // 获取首页滚动以及推荐
    List<Product> rollProducts = null;
    try {
      rollProducts = productService.getProductsRandom(ROLLPRODUCTSCOUNT).getProductList();
    } catch (Exception e) {
      logger.error(e.getMessage());
      // 失败返回空数组
      rollProducts = new ArrayList<Product>();
    }
    ProductExecution productExecution = null;
    // 获取随机展示商品
    List<ProductExecution> randomProducts = new ArrayList<ProductExecution>();
    // 随机8个种类 再从各个种类的类别里随机出4个商品
    for (ProductCategory category : RandomUtil.randomEles(categories,
        RANDOMSHOWPRODUCTSGROUPCOUNT)) {
      try {
        productExecution =
            productService.getProductsRandom(category.getId(), RANDOMSHOWPRODUCTSCOUNT);
        randomProducts.add(productExecution);
      } catch (NoProductException e) {
        // 获取此类别的随机商品失败则不存放商品
        logger.error(e.getMessage());
        continue;
      } catch (Exception e) {
        // 获取此类别的随机商品失败则不存放商品
        logger.error(e.getMessage());
        continue;
      }
    }
    // 放入model
    model.addAttribute("categories", JSON.toJSONString(categories));
    model.addAttribute("rollProducts", JSON.toJSONString(rollProducts));
    model.addAttribute("randomProducts", JSON.toJSONString(randomProducts));
    System.out.println(JSON.toJSONString(categories));
    // 前往主页
    return "/home/Home";
  }

  /**
   * 
   * <p>
   * Title: getSearch
   * </p>
   * <p>
   * Description: 前往搜索页
   * </p>
   * 
   * @param searchText 搜索关键词
   * @param model 存放搜索到的产品数据
   * @return 前往搜索页并携带产品数据和搜索关键词
   */
  @RequestMapping(value = "/search", method = RequestMethod.GET)
  private String getSearch(@RequestParam("searchText") String searchText, Model model) {
    if (StrUtil.isEmpty(searchText)) {
      return "redirect:home";
    }
    ProductExecution productExecution = null;
    try {
      productExecution = productService.searchByText(searchText, DEFAULTOFFSET, DEFAULTSEARCHLIMIT);
    } catch (NoProductException e) {
      productExecution = new ProductExecution();
      productExecution.setProductMaxCount(0);
      productExecution.setProductList(new ArrayList<Product>());
    } catch (Exception e) {
      logger.error(e.getMessage());
      productExecution = new ProductExecution();
      productExecution.setProductMaxCount(0);
      productExecution.setProductList(new ArrayList<Product>());
    }
    // 装填数据
    model.addAttribute("searchResult", JSON.toJSONString(productExecution));
    model.addAttribute("searchText", searchText);
    // 前往搜索页
    return "/product/SearchResult";
  }

  /**
   * 
   * <p>
   * Title: searchProduct
   * </p>
   * <p>
   * Description: Ajax json
   * </p>
   * 
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @param searchText 搜索关键词
   * @return Result 查询结果 里面保存了一个 ProductExecution(productMaxCount,productList)
   */
  @RequestMapping(value = "/search/{searchText}/{offset}/{limit}", method = RequestMethod.GET,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String searchProduct(@PathVariable(value = "offset") int offset,
      @PathVariable(value = "limit") int limit, @PathVariable("searchText") String searchText) {
    ProductExecution productExecution = null;
    Result<ProductExecution> result = null;
    try {
      productExecution = productService.searchByText(searchText, offset, limit);
    } catch (NoProductException e) {
      productExecution = new ProductExecution();
      productExecution.setProductMaxCount(0);
      productExecution.setProductList(new ArrayList<Product>());
    } catch (Exception e) {
      logger.error(e.getMessage());
      productExecution = new ProductExecution();
      productExecution.setProductMaxCount(0);
      productExecution.setProductList(new ArrayList<Product>());
    }
    result = new Result<ProductExecution>(true, productExecution);
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: getComments
   * </p>
   * <p>
   * Description: 获取评论信息
   * </p>
   * 
   * @param productId 商品序号
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 查询结果
   */
  @RequestMapping(value = "/products/{productId}/comments/{offset}/{limit}",
      method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String getComments(@PathVariable(value = "productId") long productId,
      @PathVariable(value = "offset") int offset, @PathVariable(value = "limit") int limit) {
    Result<List<CommentExecution>> result = null;
    try {
      List<CommentExecution> commentExecutions =
          commentService.getcommentsByProductId(productId, offset, limit).stream()
              .filter(x -> x.getState() != CommentAuditEnum.AUDIT.getState())
              .collect(Collectors.toList());
      result = new Result<List<CommentExecution>>(true, commentExecutions);
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    logger.info(JSON.toJSONString(result));
    return JSON.toJSONString(result);
  }

  @RequestMapping(value = "/products/comments", method = RequestMethod.POST,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String addComment(@RequestBody Comment comment) {
    Result<Comment> result = null;
    Comment resultComment = null;
    try {
      resultComment = commentService.addComment(comment.getProductId(), comment.getContent(),
          comment.getUserName(), comment.getStar());
      result = new Result<Comment>(true, resultComment);
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: getProductPage
   * </p>
   * <p>
   * Description: 前往商品详情页
   * </p>
   * 
   * @param model 存放商品数据 "product" ProductExecution(categoryId,categoryName,product)
   * @param productId 产品序号
   * @return 前往商品详情页
   */
  @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
  private String getProductPage(Model model, @PathVariable("productId") long productId) {
    ProductExecution productExecution = null;
    List<CommentExecution> commentExecutions = null;
    try {
      // 获取产品数据
      productExecution = productService.getProduct(productId, null);
      // 获取10条此产品评论
      commentExecutions = commentService.getcommentsByProductId(productId, 0, 10);
      commentExecutions =
          commentExecutions.stream().filter(x -> x.getState() != CommentAuditEnum.AUDIT.getState())
              .collect(Collectors.toList());
    } catch (NoProductException e) {
      throw e;
    } catch (NoCategoryException e) {
      throw e;
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw e;
    }
    // 填装数据
    logger.info(productExecution.toString());
    logger.info(JSON.toJSONString(commentExecutions));
    model.addAttribute("product", JSON.toJSONString(productExecution));
    model.addAttribute("comments", JSON.toJSONString(commentExecutions));
    // 前往商品详情页
    return "/product/WaresDetail";
  }

}
