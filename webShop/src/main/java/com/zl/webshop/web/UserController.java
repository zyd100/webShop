/**
 * <p>
 * Title: UserController.java
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
import javax.servlet.http.HttpServletRequest;
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
import com.zl.webshop.dto.OrderExecution;
import com.zl.webshop.dto.Result;
import com.zl.webshop.dto.UserExecution;
import com.zl.webshop.entity.Contact;
import com.zl.webshop.entity.OrderInfo;
import com.zl.webshop.entity.OrderItem;
import com.zl.webshop.entity.Product;
import com.zl.webshop.entity.User;
import com.zl.webshop.enums.ContactStatusEnum;
import com.zl.webshop.enums.OrderStatusEnum;
import com.zl.webshop.exception.AddToCartException;
import com.zl.webshop.exception.CreateOrderException;
import com.zl.webshop.exception.InfoEmptyException;
import com.zl.webshop.exception.NoCartException;
import com.zl.webshop.exception.NoProductException;
import com.zl.webshop.exception.NoStarException;
import com.zl.webshop.exception.NoUserException;
import com.zl.webshop.exception.NotEnoughQuantityException;
import com.zl.webshop.exception.RegisterException;
import com.zl.webshop.exception.RepeatRegisterException;
import com.zl.webshop.exception.UpdateException;
import com.zl.webshop.exception.WrongUserNamePwdException;
import com.zl.webshop.service.ContactService;
import com.zl.webshop.service.OrderService;
import com.zl.webshop.service.UserService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

/**
 * <p>
 * Title: UserController
 * </p>
 * <p>
 * Description: 用户控制器
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月25日
 *         </p>
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  /**
   * 用于添加或查询session中已登录的用户名
   */
  public static final String LOGINUSERNAME = "loginUserName";
  @Autowired
  private UserService userService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private ContactService contactService;

  /**
   * 
   * <p>
   * Title: getLoginPage
   * </p>
   * <p>
   * Description: url: /user 获取方式：get 作用：若没登录则跳转至登录/注册页面 登录了跳转至主页
   * </p>
   * 
   * @param request 获取数据
   * @return 重定向至主页或者前往至登录注册页
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  private String getLoginPage(HttpServletRequest request) {
    if (request.getSession().getAttribute(LOGINUSERNAME) != null) {
      String loginUserName = StrUtil.toString(request.getSession().getAttribute(LOGINUSERNAME));
      if (StrUtil.isNotBlank(loginUserName)) {
        // 这里仅做简单判断，如果登录了会将登录的用户名传进session
        // 已登录则转至个人主页
        System.out.println("login");
        return "redirect: /webShop/users/" + loginUserName;
      }
      // 用户名为空则返回登录注册页
      return " ";
    } else {
      // 未登录转至登录注册页
      return " ";
    }

  }

  /**
   * 
   * <p>
   * Title: tryRegister
   * </p>
   * <p>
   * Description: ajax json 尝试注册。ajax的contentype必须是"application/json; charset=utf-8"
   * 传递时必须使用JSON.stringify()封装json对象
   * </p>
   * 
   * @param register User 对象必填项为userName和password，不填项为id 选填项为nickName,email
   * @return Result 返回用Result封装好的json字符串，如果注册失败会保存失败信息error
   */
  @RequestMapping(value = "", method = RequestMethod.POST,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String tryRegister(@RequestBody User register) {
    UserExecution userExecution = null;
    Result<UserExecution> result = null;
    try {
      userExecution = userService.register(register);
      result = new Result<UserExecution>(true, userExecution);
    } catch (RepeatRegisterException e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    } catch (RegisterException e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: getPersonalCenterPage
   * </p>
   * <p>
   * Description: 若已登录则跳转至个人中心 无则跳转登录页面,前提此userName与已登录的一致
   * </p>
   * 
   * @param userName 用户名
   * @param model 存放数据
   * @param request 用来获取已登录的用户名
   * @return 成功转至个人中心 没有登录则跳转至登录页面 用户名与已登录的用户名不一致则跳转至主页
   */
  @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
  private String getPersonalCenterPage(@PathVariable String userName, HttpServletRequest request,
      Model model) {
    if (request.getSession().getAttribute(LOGINUSERNAME) == null) {
      // 未登录
      logger.error("no user");
      return "redirect: /webShop/users";
    }
    String loginUserName = StrUtil.toString(request.getSession().getAttribute(LOGINUSERNAME));
    if (ObjectUtil.notEqual(userName, loginUserName)) {
      // 传递的用户名和已登录的用户名不一致
      // 跳转至主页
      logger.error("invalid user");
      return "redirect: /webShop/home";
    }

    // 验证成功填充所需数据

    // 此用户的所有联系信息
    List<Contact> contacts = contactService.getContacts(userName);
    // 用户基本信息
    UserExecution basicUserInfo = userService.getBasicInfo(userName);
    // 用户订单信息
    OrderExecution orderExecution = orderService.getOderInfoByUserName(userName, null, 0, 10);
    List<OrderExecution>orderInfos=new ArrayList<OrderExecution>();
    //过滤购物车收藏夹
    orderExecution
        .setOrderInfos(
            orderExecution.getOrderInfos().stream()
                .filter(x -> x.getStatus() != OrderStatusEnum.SHOPPING_CART.getState()
                    && x.getStatus() != OrderStatusEnum.STAR.getState())
                .collect(Collectors.toList()));
    
    orderExecution.getOrderInfos().stream().forEach(x->orderInfos.add(orderService.getOrderDetail(x.getOrderNum(), loginUserName)));
    orderInfos.stream().forEach(x->{
     x.setOrderItemList(CollUtil.sub(x.getOrderItemList(), 0, 1));
     x.setProductList(CollUtil.sub(x.getProductList(), 0, 1));
    });
    // 装填数据
    model.addAttribute("contacts", JSON.toJSONString(contacts));
    model.addAttribute("basicUserInfo", JSON.toJSONString(basicUserInfo));
    model.addAttribute("orderInfos", JSON.toJSONString(orderInfos));
    System.out.println(JSON.toJSONString(orderInfos));
    // 前往个人中心页
    return "";
  }

  /**
   * 
   * <p>
   * Title: tryLogin
   * </p>
   * <p>
   * Description: 登录事件
   * </p>
   * 
   * @param request 登录成功则用来保存已登录的用户名
   * @param user 登录者数据
   * @return 成功返回主页 失败重定向回登录页
   */
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  private String tryLogin(User user, HttpServletRequest request) {
    boolean flag = false;
    System.out.println(user);
    try {
      flag = userService.login(user);
    } catch (Exception e) {
      logger.error(e.getMessage());
      flag = false;
    }
    if (flag == true) {
      // 登录成功
      request.getSession().setAttribute(LOGINUSERNAME, user.getUserName());
      return "redirect: /webShop/home";
    } else {
      // 登录失败
      return "redirect: /webShop/users";
    }
  }

  /**
   * 
   * <p>
   * Title: logout
   * </p>
   * <p>
   * Description: 登出函数
   * </p>
   * 
   * @param request 获取session来清除登录数据
   * @return 返回登录页
   */
  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  private String logout(HttpServletRequest request) {
    request.getSession().invalidate();
    return "redirect: /webShop/users";
  }

  /**
   * 
   * <p>
   * Title: getCart
   * </p>
   * <p>
   * Description: 前往购物车页面
   * </p>
   * 
   * @param userName 用户名
   * @param model 存放购物车数据
   * @param request 获取数据
   * @return 成功则前往购物车页面 未登录返回登录页面
   */
  @RequestMapping(value = "/{userName}/carts", method = RequestMethod.GET)
  private String getCart(@PathVariable String userName, Model model, HttpServletRequest request) {
    Result<OrderExecution> result = null;
    OrderExecution orderExecution = null;
    if (ObjectUtil.notEqual(request.getSession().getAttribute(LOGINUSERNAME), userName)) {
      // 未登录跳转至登录页面
      return "redirect: /webShop/users";
    }
    try {
      result = new Result<OrderExecution>(true, orderService.getCart(userName));
    } catch (NoCartException e) {
      orderExecution = new OrderExecution();
      orderExecution.setOrderItemList(new ArrayList<OrderItem>());
      result = new Result<>(true, orderExecution);
    } catch (Exception e) {
      orderExecution = new OrderExecution();
      orderExecution.setOrderItemList(new ArrayList<OrderItem>());
      result = new Result<>(true, orderExecution);
    }
    model.addAttribute("carts", JSON.toJSONString(result));
    System.out.println(JSON.toJSONString(result));
    // 前往购物车页面
    return "";
  }

  /**
   * 
   * <p>
   * Title: logoff
   * </p>
   * <p>
   * Description: 注销账户
   * </p>
   * 
   * @param user 用户
   * @param request 用来清除数据
   * @param userName 用户名
   * @return 删除成功返回登录页
   */
  @RequestMapping(value = "/{userName}", method = RequestMethod.DELETE)
  private String logoff(User user, @PathVariable("userName") String userName,
      HttpServletRequest request) {
    try {
      // 装填用户数据
      userService.deleteAccount(user);
    } catch (Exception e) {
      // 删除失败,重定向至个人中心页
      logger.error(e.getMessage());
      return "redirect: /webShop/homePage";
    }
    // 删除成功,清空数据
    request.getSession().invalidate();
    // 重定向至登录页
    return "redirect: /webShop/homePage";
  }

  /**
   * 
   * <p>
   * Title: addProductToCart
   * </p>
   * <p>
   * Description: 添加进购物车
   * </p>
   * 
   * @param orderItem 要添加的商品
   * @param userName 用户名
   * @return 返回添加结果 Result
   */
  @RequestMapping(value = "/{userName}/carts/{productId}", method = RequestMethod.POST,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String addProductToCart(@RequestBody OrderItem orderItem,
      @PathVariable("userName") String userName) {
    Result<OrderItem> result = null;
    try {
      orderService.addToCart(orderItem, userName);
      result = new Result<OrderItem>(true, orderItem);
    } catch (NoProductException e) {
      result = new Result<>(false, e.getMessage());
    } catch (NoUserException e) {
      result = new Result<>(false, e.getMessage());
    } catch (AddToCartException e) {
      result = new Result<>(false, e.getMessage());
    } catch (Exception e) {
      result = new Result<>(false, e.getMessage());
      logger.error(e.getMessage());
    }
    // 返回结果
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: deleteCartProduct
   * </p>
   * <p>
   * Description: 从购物车内删除商品
   * </p>
   * 
   * @param userName 用户名
   * @param orderExecution 预备要删除的商品列表
   * @return 删除结果 无论删除是否成功都会返回最新的购物车清单，但Result.success会不同
   */
  @RequestMapping(value = "/{userName}/carts", method = RequestMethod.DELETE,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String deleteCartProduct(@PathVariable("userName") String userName,
      @RequestBody OrderExecution orderExecution) {
    Result<OrderExecution> result = null;
    OrderExecution execution = null;
    try {
      // 无论删除是否成功都会返回最新的购物车清单
      // orderExecution.getOrderItemList().stream().forEach(x->orderService.removeFromCart(x,
      // userName));
      for (OrderItem orderItem : orderExecution.getOrderItemList()) {
        try {
          orderService.removeFromCart(orderItem, userName);
        } catch (NoUserException e) {
          logger.error(e.getMessage());
          throw e;
        } catch (NoProductException e) {
          logger.error(e.getMessage());
          continue;
        } catch (Exception e) {
          logger.error(e.getMessage());
          throw e;
        }
      }
      try {
        result = new Result<OrderExecution>(true, orderService.getCart(userName));
      } catch (NoCartException e) {
        execution = new OrderExecution();
        execution.setOrderItemList(new ArrayList<>());
        result = new Result<OrderExecution>(true, execution);
      } catch (Exception e) {
        logger.error(e.getMessage());
        result = new Result<>(false, e.getMessage());
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
      try {
        result = new Result<OrderExecution>(false, orderService.getCart(userName));
      } catch (NoCartException e1) {
        execution = new OrderExecution();
        execution.setOrderItemList(new ArrayList<>());
        result = new Result<OrderExecution>(true, execution);
      } catch (Exception e1) {
        logger.error(e1.getMessage());
        result = new Result<>(false, e1.getMessage());
      }
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: getStarPage
   * </p>
   * <p>
   * Description: 前往收藏夹页面 未写异常处理
   * </p>
   * 
   * @param userName 用户名
   * @param model 存放数据
   * @param request 获取数据
   * @return 成功则前往收藏夹页面
   */
  @RequestMapping(value = "/{userName}/stars", method = RequestMethod.GET)
  private String getStarPage(@PathVariable("userName") String userName, Model model,
      HttpServletRequest request) {
    String loginUserName = StrUtil.toString(request.getSession().getAttribute(LOGINUSERNAME));
    if (StrUtil.isEmpty(loginUserName) || ObjectUtil.notEqual(userName, loginUserName)) {
      // 没有登录,跳转至登陆页面或非法访问未登录用户页面
      return "redirect: /webShop/users";
    }
    try {
      OrderExecution orderExecution = orderService.getStar(userName);
      orderExecution.setOrderItemList(CollUtil.sub(orderExecution.getOrderItemList(), 0, 12));
      model.addAttribute("stars", JSON.toJSONString(orderExecution));
    } catch (Exception e) {
      logger.error(e.getMessage());
    }

    // 前往收藏夹页面
    return "";
  }

  /**
   * 
   * <p>
   * Title: starProduct
   * </p>
   * <p>
   * Description: 收藏一个商品
   * </p>
   * 
   * @param userName 用户名
   * @param productId 商品序号
   * @return Result
   */
  @RequestMapping(value = "/{userName}/stars/{productId}", method = RequestMethod.POST)
  @ResponseBody
  private String starProduct(@PathVariable("userName") String userName,
      @PathVariable("productId") long productId) {
    Result<Product> result = null;
    try {
      // 尝试添加收藏商品
      OrderItem orderItem = new OrderItem();
      orderItem.setProductId(productId);
      orderService.addToStar(orderItem, userName);
      // 存放收藏结果
      Product product = new Product();
      product.setId(productId);
      result = new Result<>(true, product);
    } catch (NoProductException e) {
      result = new Result<>(false, e.getMessage());
    } catch (NoUserException e) {
      result = new Result<>(false, e.getMessage());
    } catch (UpdateException e) {
      result = new Result<>(false, e.getMessage());
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: getStars
   * </p>
   * <p>
   * Description: ajax 获取收藏夹商品
   * </p>
   * 
   * @param userName 用户名
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 查询结果
   */
  @RequestMapping(value = "/{userName}/stars/{offset}/{limit}", method = RequestMethod.GET)
  @ResponseBody
  private String getStars(@PathVariable("userName") String userName,
      @PathVariable("offset") int offset, @PathVariable("limit") int limit) {
    Result<OrderExecution> result = null;
    try {
      OrderExecution orderExecution = orderService.getStar(userName);
      // 根据offset limit 剪切收藏夹商品数组长度
      orderExecution.setOrderItemList(
          CollUtil.sub(orderExecution.getOrderItemList(), offset, offset + limit));
      result = new Result<OrderExecution>(true, orderExecution);
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: deleteStarProduct
   * </p>
   * <p>
   * Description: 删除收藏夹的商品
   * </p>
   * 
   * @param userName 用户名
   * @param orderExecution 内保存要删除的商品列表 每个商品必须含有id orderItem.id
   * @return 无论删除是否成功都会返回最新的收藏夹清单
   */
  @RequestMapping(value = "/{userName}/stars", method = RequestMethod.DELETE,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String deleteStarProduct(@PathVariable("userName") String userName,
      @RequestBody OrderExecution orderExecution) {
    OrderExecution execution = null;
    Result<OrderExecution> result = null;
    try {
      // 无论删除是否成功都会返回最新的收藏夹清单
      // orderExecution.getOrderItemList().stream().forEach(x->orderService.removeFromCart(x,
      // userName));
      for (OrderItem orderItem : orderExecution.getOrderItemList()) {
        try {
          orderService.removeFromStar(orderItem, userName);
        } catch (NoUserException e) {
          throw e;
        } catch (NoProductException e) {
          continue;
        } catch (Exception e) {
          logger.error(e.getMessage());
          throw e;
        }
      }
      try {
        result = new Result<OrderExecution>(true, orderService.getStar(userName));
      } catch (NoStarException e) {
        execution = new OrderExecution();
        execution.setOrderItemList(new ArrayList<>());
        result = new Result<OrderExecution>(true, execution);
      } catch (Exception e2) {
        logger.error(e2.getMessage());
        throw e2;
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
      try {
        result = new Result<OrderExecution>(false, orderService.getStar(userName));
      } catch (NoStarException e1) {
        execution = new OrderExecution();
        execution.setOrderItemList(new ArrayList<>());
        result = new Result<OrderExecution>(true, execution);
      } catch (Exception e2) {
        logger.error(e2.getMessage());
        throw e2;
      }
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: getUserContacts
   * </p>
   * <p>
   * Description: 获取此用户的所有联系信息
   * </p>
   * 
   * @param userName 用户名
   * @return 获取结果 Result
   */
  @RequestMapping(value = "/{userName}/contacts", method = RequestMethod.GET,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String getUserContacts(@PathVariable("userName") String userName) {
    List<Contact> contacts = contactService.getContacts(userName);
    Result<List<Contact>> result = null;
    if (contacts != null) {
      result = new Result<List<Contact>>(true, contacts);
    } else {
      result = new Result<List<Contact>>(true, new ArrayList<>());
    }

    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: addUserContacts
   * </p>
   * <p>
   * Description: 新增一个联系地址
   * </p>
   * 
   * @param userName 用户名
   * @param contact 联系信息
   * @return 新增结果 Result
   */
  @RequestMapping(value = "/{userName}/contacts", method = RequestMethod.POST,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String addUserContacts(@PathVariable("userName") String userName,
      @RequestBody Contact contact) {

    Result<Contact> result = null;

    try {
      contactService.addContact(contact);
      if (contact.getStatus() == ContactStatusEnum.DEFAULT.getState()) {
        contactService.setDefaultContact(contact.getId(), userName);
        result = new Result<Contact>(true, contact);
      } else {
        result = new Result<Contact>(true, contact);
      }

    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: deleteUserContacts
   * </p>
   * <p>
   * Description: 删除一个联系地址
   * </p>
   * 
   * @param userName 用户名
   * @param contact 联系地址
   * @return 删除结果
   */
  @RequestMapping(value = "/{userName}/contacts", method = RequestMethod.DELETE,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String deleteUserContacts(@PathVariable("userName") String userName,
      @RequestBody Contact contact) {

    Result<Contact> result = null;

    try {
      contactService.removeContact(contact);
      result = new Result<Contact>(true, contact);
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: getOrderHistory
   * </p>
   * <p>
   * Description: ajax 获取用户的订单历史
   * </p>
   * 
   * @param userName 用户名
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 查询结果 Result
   */
  @RequestMapping(value = "/{userName}/orderHistory", method = RequestMethod.GET,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String getOrderHistory(@PathVariable("userName") String userName,
      @RequestParam(value = "offset", defaultValue = "0") int offset,
      @RequestParam(value = "limit", defaultValue = "10") int limit) {
    Result<OrderExecution> result = null;
    try {
      OrderExecution orderExecution = orderService.getOderInfoByUserName(userName, null, 0, 10);
      orderExecution
          .setOrderInfos(orderExecution.getOrderInfos().stream()
              .filter(x -> x.getStatus() != OrderStatusEnum.SHOPPING_CART.getState()
                  && x.getStatus() != OrderStatusEnum.STAR.getState())
              .collect(Collectors.toList()));
      result = new Result<OrderExecution>(true, orderExecution);
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }

    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: getOrderHistoryDetailPage
   * </p>
   * <p>
   * Description: 前往订单详情页查看订单详情
   * </p>
   * 
   * @param userName 用户名
   * @param orderNum 订单编号
   * @param model 存放数据
   * @param request 获取数据
   * @return 成功前往订单详情页
   */
  @RequestMapping(value = "/{userName}/orderHistory/{orderNum}", method = RequestMethod.GET)
  private String getOrderHistoryDetailPage(@PathVariable("userName") String userName,
      @PathVariable("orderNum") String orderNum, Model model, HttpServletRequest request) {

    String loginUserName = StrUtil.toString(request.getSession().getAttribute(LOGINUSERNAME));
    if (StrUtil.isEmpty(loginUserName) || ObjectUtil.notEqual(userName, loginUserName)) {
      // 没有登录,跳转至登陆页面或非法访问未登录用户页面
      return "redirect: /webShop/users";
    }
    OrderExecution orderExecution = orderService.getOrderDetail(orderNum, userName);
    if (null == orderExecution.getOrderInfo()) {
      orderExecution.setOrderInfo(new OrderInfo());
    }
    if (null == orderExecution.getOrderItemList()) {
      orderExecution.setOrderItemList(new ArrayList<>());
    }
    model.addAttribute("orderExecution", JSON.toJSONString(orderExecution));
    logger.debug(JSON.toJSONString(orderExecution));
    // 前往订单详情页
    return "";
  }

  /**
   * 
   * <p>
   * Title: deleteOrder
   * </p>
   * <p>
   * Description:删除订单信息，并从删除后的订单列表中返回10条订单信息
   * </p>
   * 
   * @param userName 用户名
   * @param orderExecution 保存想要删除的订单列表
   * @return 返回最新的10条订单
   */
  @RequestMapping(value = "/{userName}/orderHistory", method = RequestMethod.DELETE,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String deleteOrder(@PathVariable("userName") String userName,
      @RequestBody OrderExecution orderExecution) {
    Result<OrderExecution> result = null;
    OrderExecution resultExecution = null;
    try {
      // 遍历删除
      for (OrderInfo orderInfo : orderExecution.getOrderInfos()) {
        orderService.deleteOrder(orderInfo.getOrderNum());
      }
      // 获取10条订单历史
      resultExecution = orderService.getOderInfoByUserName(userName, null, 0, 10);
      resultExecution
          .setOrderInfos(resultExecution.getOrderInfos().stream()
              .filter(x -> x.getStatus() != OrderStatusEnum.SHOPPING_CART.getState()
                  && x.getStatus() != OrderStatusEnum.STAR.getState())
              .collect(Collectors.toList()));
      result = new Result<OrderExecution>(true, resultExecution);

    } catch (Exception e) {
      // 删除失败，获取最新的历史订单放入结果
      logger.error(e.getMessage());
      resultExecution = orderService.getOderInfoByUserName(userName, null, 0, 10);
      resultExecution
          .setOrderInfos(resultExecution.getOrderInfos().stream()
              .filter(x -> x.getStatus() != OrderStatusEnum.SHOPPING_CART.getState()
                  && x.getStatus() != OrderStatusEnum.STAR.getState())
              .collect(Collectors.toList()));
      result = new Result<OrderExecution>(false, resultExecution);
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: updateUserBasicInfo
   * </p>
   * <p>
   * Description: 更新用户的基本信息 不包括密码
   * </p>
   * 
   * @param user 用户信息 User
   * @param request 获取数据
   * @param userName 请求的用户名
   * @return 更新结果
   */
  @RequestMapping(value = "/{userName}", method = RequestMethod.PUT,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String updateUserBasicInfo(@RequestBody User user, HttpServletRequest request,
      @PathVariable("userName") String userName) {

    Result<UserExecution> result = null;
    try {
      String loginUserName = StrUtil.toString(request.getSession().getAttribute(LOGINUSERNAME));
      if (ObjectUtil.isNull(request.getSession().getAttribute(LOGINUSERNAME))
          || ObjectUtil.notEqual(userName, loginUserName)) {
        // 用户名与已登录的用户名不一致
        throw new NoUserException("invalid request");
      }
      System.out.println(user);
      userService.updateUserInfo(user);
      result = new Result<UserExecution>(true, userService.getBasicInfo(user.getUserName()));
    } catch (NoUserException e) {
      result = new Result<>(false, e.getMessage());
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: resetPassword
   * </p>
   * <p>
   * Description: 重设密码
   * </p>
   * 
   * @param oldPassword 原密码
   * @param newPassword 新密码
   * @param request 获取数据
   * @param userName 用户名
   * @return 修改结果
   */
  @RequestMapping(value = "/{userName}/password", method = RequestMethod.PUT,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String resetPassword(@RequestParam("oldPassword") String oldPassword,
      @RequestParam("newPassword") String newPassword, HttpServletRequest request,
      @PathVariable("userName") String userName) {
    Result<Boolean> result = null;
    try {
      String loginUserName = StrUtil.toString(request.getSession().getAttribute(LOGINUSERNAME));
      if (ObjectUtil.isNull(request.getSession().getAttribute(LOGINUSERNAME))
          || ObjectUtil.notEqual(userName, loginUserName)) {
        // 用户名与已登录的用户名不一致
        throw new NoUserException("invalid request");
      }
      if (userService.checkPassword(userName, oldPassword) == false) {
        // 密码错误
        throw new WrongUserNamePwdException("wrong password");
      } else {
        User user = new User();
        user.setPassword(newPassword);
        user.setUserName(userName);
        boolean flag = userService.updatePassword(user) > 0 ? true : false;
        result = new Result<Boolean>(flag, flag);
      }

    } catch (WrongUserNamePwdException e) {
      result = new Result<>(false, e.getMessage());
    } catch (NoUserException e) {
      result = new Result<>(false, e.getMessage());
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }

  /**
   * 
   * <p>
   * Title: getOrderPage
   * </p>
   * <p>
   * Description: 前往订单页面
   * </p>
   * 
   * @param orderExecution 存放要购买的物品清单orderItemList
   * @param model 转发购物清单至订单页面
   * @param userName 用户名
   * @param request 获取数据
   * @return 订单页面
   */
  @RequestMapping(value = "/{userName}/carts/order", method = RequestMethod.POST)
  private String getOrderPage(OrderExecution orderExecution, Model model,
      @PathVariable("userName") String userName, HttpServletRequest request) {
    String loginUserName = StrUtil.toString(request.getSession().getAttribute(LOGINUSERNAME));
    if (ObjectUtil.isNull(request.getSession().getAttribute(LOGINUSERNAME))
        || ObjectUtil.notEqual(userName, loginUserName)) {
      // 用户名与已登录的用户名不一致
      // 重定向至登录页
      return "redirect: /webShop/users";
    }
    model.addAttribute("orderExecution", JSON.toJSONString(orderExecution));
    logger.debug(JSON.toJSONString(orderExecution));
    // 前往订单支付页
    return "";
  }

  /**
   * 
   * <p>
   * Title: buyProducts
   * </p>
   * <p>
   * Description: ajax下单购买产品
   * </p>
   * 
   * @param userName 用户名
   * @param orderExecution 存放购买相关信息
   * @return 购买结果
   */
  @RequestMapping(value = "/{userName}/order", method = RequestMethod.POST,
      produces = {"application/json; charset=utf-8"})
  @ResponseBody
  private String buyProducts(@PathVariable("userName") String userName,
      @RequestBody OrderExecution orderExecution) {
    Result<OrderExecution> result = null;
    Contact contact = null;
    try {
      contact = new Contact();
      contact.setContactAddress(orderExecution.getOrderInfo().getContactAddress());
      contact.setContactMobile(orderExecution.getOrderInfo().getContactMobile());
      contact.setContactName(orderExecution.getOrderInfo().getContactName());
      
      //根据商品数量设置不同购买方式
      int minSize=2;
      if (orderExecution.getOrderItemList().size() < minSize) {
        // 立即下单购买一个商品
        result = new Result<OrderExecution>(true,
            orderService.buyOneNow(orderExecution.getOrderItemList().get(0), userName, contact,
                orderExecution.getOrderInfo().getMessage()));
      } else {
        // 购买购物车内的商品
        result = new Result<OrderExecution>(true, orderService.buyGoods(orderExecution));
      }
    } catch (NotEnoughQuantityException e) {
      result = new Result<>(false, e.getMessage());
    } catch (NoUserException e) {
      result = new Result<>(false, e.getMessage());
    } catch (CreateOrderException e) {
      result = new Result<>(false, e.getMessage());
    } catch (InfoEmptyException e) {
      result = new Result<>(false, e.getMessage());
    } catch (Exception e) {
      logger.error(e.getMessage());
      result = new Result<>(false, e.getMessage());
    }
    return JSON.toJSONString(result);
  }
}
