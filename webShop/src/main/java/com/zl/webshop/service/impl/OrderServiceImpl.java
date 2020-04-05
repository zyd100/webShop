/**
 * <p>
 * Title: OrderServiceImpl.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月21日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zl.webshop.dao.OrderHistoryDao;
import com.zl.webshop.dao.OrderInfoDao;
import com.zl.webshop.dao.OrderItemDao;
import com.zl.webshop.dao.ProductDao;
import com.zl.webshop.dao.UserDao;
import com.zl.webshop.dto.OrderExecution;
import com.zl.webshop.entity.Contact;
import com.zl.webshop.entity.OrderHistory;
import com.zl.webshop.entity.OrderInfo;
import com.zl.webshop.entity.OrderItem;
import com.zl.webshop.entity.Product;
import com.zl.webshop.enums.OrderStatusEnum;
import com.zl.webshop.exception.AddToCartException;
import com.zl.webshop.exception.CartStatusException;
import com.zl.webshop.exception.CreateOrderException;
import com.zl.webshop.exception.DeleteException;
import com.zl.webshop.exception.InfoEmptyException;
import com.zl.webshop.exception.NoCartException;
import com.zl.webshop.exception.NoProductException;
import com.zl.webshop.exception.NoStarException;
import com.zl.webshop.exception.NoUserException;
import com.zl.webshop.exception.NotEnoughQuantityException;
import com.zl.webshop.exception.ProductLostException;
import com.zl.webshop.exception.StarStatusException;
import com.zl.webshop.exception.UpdateException;
import com.zl.webshop.service.OrderService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;

/**
 * <p>
 * Title: OrderServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月21日
 *         </p>
 */
@Service
public class OrderServiceImpl implements OrderService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private OrderInfoDao orderInfoDao;
  @Autowired
  private OrderItemDao orderItemDao;
  @Autowired
  private OrderHistoryDao orderHistoryDao;
  @Autowired
  private ProductDao productDao;
  @Autowired
  private UserDao userDao;

  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public int addToCart(OrderItem orderItem, String userName) {
    int index = 0;
    int standardCount = 2;
    try {
      // 获取后台真实产品数据
      Product realProduct = productDao.queryById(orderItem.getProductId());
      if (null == realProduct) {
        // 获取产品失败
        throw new NoProductException("no Product");
      }
      if (null == userDao.queryByUserName(userName)) {
        // 获取用户失败
        throw new NoUserException("no User");
      }
      // 购物车编号由‘000000’+userName组成
      String orderNum = DigestUtil.md5Hex("000000" + userName);
      if (null == orderInfoDao.queryByOrderNum(orderNum)) {
        // 找不到购物车订单，创建购物车
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderNum(orderNum);
        orderInfo.setStatus(OrderStatusEnum.SHOPPING_CART.getState());
        orderInfo.setUserName(userName);
        // 总金额
        orderInfo.setPrice(realProduct.getShopPrice() * orderItem.getQuantity());
        // 重置orderItem数据
        orderItem.setOrderNum(orderNum);
        orderItem.setPrice(realProduct.getShopPrice());
        orderItem.setProductId(realProduct.getId());
        orderItem.setProductName(realProduct.getProductName());
        orderItem.setUserName(userName);
        // 存入数据库
        index += orderInfoDao.addOrderInfo(orderInfo);
        index += orderItemDao.addOrderItem(orderItem);
        if (index < standardCount) {
          throw new AddToCartException("add to Cart error");
        }
      } else {
        // 已有购物车订单
        // 重置orderItem数据
        orderItem.setOrderNum(orderNum);
        orderItem.setPrice(realProduct.getShopPrice());
        orderItem.setProductId(realProduct.getId());
        orderItem.setProductName(realProduct.getProductName());
        orderItem.setUserName(userName);

        // 更新并存入数据库
        index += orderItemDao.addOrderItem(orderItem);
        // 修改购物车总金额
        OrderInfo orderInfo = orderInfoDao.queryByOrderNum(orderNum);
        orderInfo.setPrice(0);
        orderItemDao.queryByUserNameAndOrderNum(userName, orderNum).stream().forEach(
            x -> orderInfo.setPrice(orderInfo.getPrice() + x.getPrice() * x.getQuantity()));
        index += orderInfoDao.updateOrderInfo(orderInfo);
        if (index < standardCount) {
          throw new AddToCartException("add to Cart error");
        }
      }
    } catch (NoProductException e) {
      throw e;
    } catch (NoUserException e) {
      throw e;
    } catch (AddToCartException e) {
      throw e;
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new UpdateException("addToCart inner error:" + e.getMessage());
    }
    return index;
  }

  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public int removeFromCart(OrderItem orderItem, String userName) {
    int index = 0;
    try {
      // 获取后台真实产品数据
      Product realProduct = productDao.queryById(orderItem.getProductId());
      if (null == realProduct) {
        // 获取产品失败
        throw new NoProductException("no Product");
      }
      if (null == userDao.queryByUserName(userName)) {
        // 获取用户失败
        throw new NoUserException("no User");
      }
      // 购物车编号由‘000000’+userName组成
      String orderNum = DigestUtil.md5Hex("000000" + userName);
      // 获取购物车信息
      OrderInfo orderInfo = orderInfoDao.queryByOrderNum(orderNum);
      // 尝试删除购物车商品
      index += orderItemDao.deleteOrderItem(orderItem);
      // 重设购物车总金额
      orderInfo.setPrice(orderInfo.getPrice() - orderItem.getPrice() * orderItem.getQuantity());
      if (orderItemDao.countByOrderNum(orderNum) == 0) {
        // 如果购物车内无商品，删除购物车订单
        index += orderInfoDao.deleteOrderInfo(orderInfo);
      } else {
        // 有则更新购物车总金额
        index += orderInfoDao.updateOrderInfo(orderInfo);
      }
    } catch (NoProductException e) {
      throw e;
    } catch (NoUserException e) {
      throw e;
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new DeleteException("removeFromCart inner error:" + e.getMessage());
    }
    return index;
  }

  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public int updateProductQuantityInCart(OrderItem orderItem, String userName) {
    int index = 0;
    try {
      if (ObjectUtil.isEmpty(userDao.queryByUserName(userName))) {
        throw new NoUserException("no user");
      }
      if (ObjectUtil.isAllEmpty(orderItem.getOrderNum(), orderItem.getProductId(),
          orderItem.getQuantity())) {
        // 信息缺失
        throw new InfoEmptyException("empty information");
      }
      Product product = productDao.queryById(orderItem.getProductId());
      if (ObjectUtil.isEmpty(product)) {
        // 查无此产品
        throw new NoProductException("no product");
      }
      if (product.getQuantity() < orderItem.getQuantity()) {
        // 库存不足
        throw new NotEnoughQuantityException("insufficient inventory");
      }
      OrderItem oldItem = orderItemDao.queryById(orderItem.getId());
      index = orderItemDao.updateOrderItem(orderItem);
      // 更新购物车总价格
      OrderInfo orderInfo = orderInfoDao.queryByOrderNum(orderItem.getOrderNum());
      orderInfo.setPrice(orderInfo.getPrice() - (oldItem.getPrice() * oldItem.getQuantity())
          + (orderItem.getPrice() * orderItem.getQuantity()));
      orderInfoDao.updateOrderInfo(orderInfo);
    } catch (NoUserException e) {
      throw e;
    } catch (NoProductException e) {
      throw e;
    } catch (InfoEmptyException e) {
      throw e;
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new RuntimeException("updateProductQuantityInCart inner error:" + e.getMessage());
    }
    return index;
  }

  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public OrderExecution buyOneNow(OrderItem orderItem, String userName, Contact contact,
      String message) {
    OrderExecution orderExecution = null;
    int standardCount = 1;
    try {
      if (StrUtil.isAllEmpty(contact.getContactAddress(), contact.getContactName(),
          contact.getContactMobile(), userName)) {
        throw new InfoEmptyException("empty information");
      }
      if (null == userDao.queryByUserName(userName)) {
        throw new NoUserException("no user");
      }
      String orderNum = IdUtil.simpleUUID();
      Product product = productDao.queryById(orderItem.getProductId());
      if (product.getQuantity() < orderItem.getQuantity()) {
        // 购买数量大于产品拥有数量
        throw new NotEnoughQuantityException("insufficient inventory");
      }
      // 新建订单
      OrderInfo orderInfo = new OrderInfo();
      orderInfo.setOrderNum(orderNum);
      orderInfo.setUserName(userName);
      orderInfo.setContactAddress(contact.getContactAddress());
      orderInfo.setContactMobile(contact.getContactMobile());
      orderInfo.setContactName(contact.getContactName());
      orderInfo.setPrice(product.getShopPrice() * orderItem.getQuantity());
      orderInfo.setStatus(OrderStatusEnum.ORDER_PAYED.getState());
      orderInfo.setMessage(message);
      if (orderInfoDao.addOrderInfo(orderInfo) < standardCount) {
        throw new CreateOrderException("createOrderInfo error");
      }
      // 设置订单条目
      orderItem.setUserName(userName);
      orderItem.setOrderNum(orderNum);
      orderItem.setProductName(product.getProductName());
      orderItem.setPrice(product.getShopPrice());
      if (orderItemDao.addOrderItem(orderItem) < standardCount) {
        throw new CreateOrderException("createOrderItem error");
      }
      // 设置订单历史
      OrderHistory orderHistory = new OrderHistory();
      orderHistory.setOrderNum(orderNum);
      orderHistory.setStatus(OrderStatusEnum.ORDER_PAYED.getState());
      orderHistory.setCreateTime(orderInfoDao.queryByOrderNum(orderNum).getCreateTime());
      if (orderHistoryDao.addOrderHistory(orderHistory) < standardCount) {
        throw new CreateOrderException("createOrderHistory error");
      }
      // 更新库存
      product.setQuantity(product.getQuantity() - orderItem.getQuantity());
      productDao.updateProduct(product);
      // 购买成功返回信息
      orderExecution = new OrderExecution(orderInfo, OrderStatusEnum.ORDER_PAYED);
    } catch (NotEnoughQuantityException e) {
      throw e;
    } catch (NoUserException e) {
      throw e;
    } catch (CreateOrderException e) {
      throw e;
    } catch (InfoEmptyException e) {
      throw e;
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new RuntimeException("buyOneNow inner error:" + e.getMessage());
    }
    return orderExecution;
  }

  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public OrderExecution buyGoods(OrderExecution orderExecution) {
    OrderExecution result = null;
    try {
      if (StrUtil.isAllEmpty(orderExecution.getOrderInfo().getContactAddress(),
          orderExecution.getOrderInfo().getContactName(),
          orderExecution.getOrderInfo().getContactMobile(),
          orderExecution.getOrderInfo().getUserName())) {
        throw new InfoEmptyException("empty information");
      }
      if (null == userDao.queryByUserName(orderExecution.getOrderInfo().getUserName())) {
        throw new NoUserException("no user");
      }
      // 设置订单
      String orderNum = IdUtil.simpleUUID();
      orderExecution.getOrderInfo().setOrderNum(orderNum);
      orderExecution.getOrderInfo().setStatus(OrderStatusEnum.ORDER_PAYED.getState());
      orderInfoDao.addOrderInfo(orderExecution.getOrderInfo());

    /*  // 删除购物车内选定要购买的产品
      orderExecution.getOrderItemList().forEach(x -> orderItemDao.deleteOrderItem(x));
      if (orderItemDao
          .countByOrderNum(orderExecution.getOrderItemList().get(0).getOrderNum()) < 1) {
        // 购物车内产品全清空，则删除订单
        orderInfoDao.deleteOrderInfo(
            orderInfoDao.queryByOrderNum(orderExecution.getOrderItemList().get(0).getOrderNum()));
      }*/
      // 遍历要购买的产品
      float totalPrice = 0f;
      for (OrderItem orderItem : orderExecution.getOrderItemList()) {
        Product product = productDao.queryById(orderItem.getProductId());
        if (product.getQuantity() < orderItem.getQuantity()) {
          // 购买数量大于产品拥有数量
          throw new NotEnoughQuantityException("insufficient inventory");
        }
        // 校正购买的产品数据
        orderItem.setOrderNum(orderNum);
        orderItem.setPrice(product.getShopPrice());
        orderItem.setProductName(product.getProductName());
        orderItem.setUserName(orderExecution.getOrderInfo().getUserName());
        // 计算总价格
        totalPrice += orderItem.getPrice() * orderItem.getQuantity();
        // 添加订单条目
        orderItemDao.addOrderItem(orderItem);
        // 更新库存
        product.setQuantity(product.getQuantity() - orderItem.getQuantity());
        productDao.updateProduct(product);
      }
      // 更新总价格
      orderExecution.getOrderInfo().setPrice(totalPrice);
      System.out.println(orderExecution.getOrderInfo().getPrice());
      System.out.println(orderInfoDao.updateOrderInfo(orderExecution.getOrderInfo()));
      // 新增订单历史
      OrderHistory orderHistory = new OrderHistory();
      orderHistory.setOrderNum(orderNum);
      orderHistory.setStatus(OrderStatusEnum.ORDER_PAYED.getState());
      orderHistory.setCreateTime(orderInfoDao.queryByOrderNum(orderNum).getCreateTime());
      orderHistoryDao.addOrderHistory(orderHistory);
      // 设置返回
      result =
          new OrderExecution(orderInfoDao.queryByOrderNum(orderNum), OrderStatusEnum.ORDER_PAYED);

    } catch (NotEnoughQuantityException e) {
      throw e;
    } catch (NoUserException e) {
      throw e;
    } catch (CreateOrderException e) {
      throw e;
    } catch (InfoEmptyException e) {
      throw e;
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new RuntimeException("buyGoods inner error:" + e.getMessage());
    }
    return result;
  }

  @Override
  public OrderExecution getCart(String userName) {
    OrderExecution orderExecution = null;
    try {
      if (null == userDao.queryByUserName(userName)) {
        // 获取用户失败
        throw new NoUserException("no User");
      }
      // 购物车编号由‘000000’+userName组成
      String orderNum = DigestUtil.md5Hex("000000" + userName);
      OrderInfo orderInfo = orderInfoDao.queryByOrderNum(orderNum);
      if (orderInfo == null) {
        throw new NoCartException("no cart");
      }
      if (ObjectUtil.notEqual(OrderStatusEnum.SHOPPING_CART,
          OrderStatusEnum.stateOf(orderInfo.getStatus()))) {
        throw new CartStatusException("cartOrder status error");
      }
      List<OrderItem> orderItems = orderItemDao.queryByUserNameAndOrderNum(userName, orderNum);
      if (orderItems.size() < 1) {
        throw new NoCartException("no cart");
      }
      //限制购物车长度
      orderItems=CollUtil.sub(orderItems, 0, 12);
      List<Product> products = new ArrayList<Product>();
      orderItems.forEach(x -> products.add(productDao.queryById(x.getProductId())));
      if (orderItems.size() != products.size()) {
        throw new ProductLostException("can not find product");
      }
      orderExecution = new OrderExecution(orderInfo, orderItems, products,
          OrderStatusEnum.stateOf(orderInfo.getStatus()));
    } catch (NoUserException e) {
      throw e;
    } catch (NoCartException e) {
      throw e;
    } catch (ProductLostException e) {
      throw e;
    } catch (CartStatusException e) {
      throw e;
    }catch (Exception e) {
      logger.error(e.getMessage());
      throw new RuntimeException("getCart inner error:" + e.getMessage());
    }
    return orderExecution;
  }

  @Override
  public OrderExecution getOderInfoByUserName(String userName, OrderStatusEnum status, int offset,
      int limit) {
    OrderExecution orderExecution = null;
    List<OrderInfo> orderInfos=null;
    try {
      if (null == userDao.queryByUserName(userName)) {
        // 获取用户失败
        throw new NoUserException("no User");
      }
      if (status != null) {
        // 过滤掉非此状态的订单
        orderInfos = orderInfoDao.queryByUserName(userName, offset, limit).stream()
            .filter(x -> x.getStatus() != status.getState()).collect(Collectors.toList());
      } else {
          orderInfos=orderInfoDao.queryByUserName(userName, offset, limit);
      }
      orderExecution=new OrderExecution();
      //装填数据
      orderExecution.setOrderInfos(orderInfos);
      if(ObjectUtil.isNotEmpty(status)) {
        orderExecution.setState(status.getState());
        orderExecution.setStateInfo(status.getStateInfo());
      }
    }catch (NoUserException e) {
      throw e;
    } 
    catch (Exception e) {
      logger.error(e.getMessage());
      throw e;
    }
    return orderExecution;
  }

  @Override
  public int addToStar(OrderItem orderItem, String userName) {
    int index = 0;
    int standardCount = 2;
    try {
      // 获取后台真实产品数据
      Product realProduct = productDao.queryById(orderItem.getProductId());
      if (null == realProduct) {
        // 获取产品失败
        throw new NoProductException("no Product");
      }
      if (null == userDao.queryByUserName(userName)) {
        // 获取用户失败
        throw new NoUserException("no User");
      }
      // 购物车编号由‘111111’+userName组成
      String orderNum = DigestUtil.md5Hex("111111" + userName);
      if (null == orderInfoDao.queryByOrderNum(orderNum)) {
        // 找不到收藏夹订单，创建购物车
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderNum(orderNum);
        orderInfo.setStatus(OrderStatusEnum.STAR.getState());
        orderInfo.setUserName(userName);
        
        // 重置orderItem数据
        orderItem.setOrderNum(orderNum);
        orderItem.setPrice(realProduct.getShopPrice());
        orderItem.setProductId(realProduct.getId());
        orderItem.setProductName(realProduct.getProductName());
        orderItem.setUserName(userName);
        orderItem.setQuantity(1);
        // 存入数据库
        index += orderInfoDao.addOrderInfo(orderInfo);
        index += orderItemDao.addOrderItem(orderItem);
        if (index < standardCount) {
          throw new UpdateException("add to star error");
        }
      } else {
        // 已有购物车订单
        // 重置orderItem数据
        orderItem.setOrderNum(orderNum);
        orderItem.setPrice(realProduct.getShopPrice());
        orderItem.setProductId(realProduct.getId());
        orderItem.setProductName(realProduct.getProductName());
        orderItem.setUserName(userName);
        orderItem.setQuantity(1);
        // 更新并存入数据库
        index += orderItemDao.addOrderItem(orderItem);
        if (index < standardCount-1) {
          throw new UpdateException("add to star error");
        }
      }
    } catch (NoProductException e) {
      throw e;
    } catch (NoUserException e) {
      throw e;
    } catch (UpdateException e) {
      throw e;
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new UpdateException("addToStar inner error:" + e.getMessage());
    }
    return index;
  }

  @Override
  public int removeFromStar(OrderItem orderItem, String userName) {
    int index = 0;
    try {
      // 获取后台真实产品数据
      Product realProduct = productDao.queryById(orderItem.getProductId());
      if (null == realProduct) {
        // 获取产品失败
        throw new NoProductException("no Product");
      }
      if (null == userDao.queryByUserName(userName)) {
        // 获取用户失败
        throw new NoUserException("no User");
      }
      // 收藏夹编号由‘111111’+userName组成
      String orderNum = DigestUtil.md5Hex("111111" + userName);
      // 获取收藏夹信息
      OrderInfo orderInfo = orderInfoDao.queryByOrderNum(orderNum);
      // 尝试删除收藏夹商品
      orderItem.setUserName(userName);
      index += orderItemDao.deleteOrderItem(orderItem);
      if (orderItemDao.countByOrderNum(orderNum) == 0) {
        // 如果购物车内无商品，删除购物车订单
        index += orderInfoDao.deleteOrderInfo(orderInfo);
      } 
    } catch (NoProductException e) {
      throw e;
    } catch (NoUserException e) {
      throw e;
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new DeleteException("removeFromStar inner error:" + e.getMessage());
    }
    return index;
  }

  @Override
  public OrderExecution getStar(String userName) {
    OrderExecution orderExecution = null;
    try {
      if (null == userDao.queryByUserName(userName)) {
        // 获取用户失败
        throw new NoUserException("no User");
      }
      // 收藏夹编号由‘111111’+userName组成
      String orderNum = DigestUtil.md5Hex("111111" + userName);
      OrderInfo orderInfo = orderInfoDao.queryByOrderNum(orderNum);
      if (orderInfo == null) {
        throw new NoStarException("no star");
      }
      if (ObjectUtil.notEqual(OrderStatusEnum.STAR,
          OrderStatusEnum.stateOf(orderInfo.getStatus()))) {
        throw new StarStatusException("star status error");
      }
      List<OrderItem> orderItems = orderItemDao.queryByUserNameAndOrderNum(userName, orderNum);
      if (orderItems.size() < 1) {
        throw new NoStarException("no star");
      }
      List<Product> products = new ArrayList<Product>();
      orderItems.forEach(x -> products.add(productDao.queryById(x.getProductId())));
      if (orderItems.size() != products.size()) {
        throw new ProductLostException("can not find product");
      }
      orderExecution = new OrderExecution(orderInfo, orderItems, products,
          OrderStatusEnum.stateOf(orderInfo.getStatus()));
    } catch (NoUserException e) {
      throw e;
    } catch (NoStarException e) {
      throw e;
    } catch (ProductLostException e) {
      throw e;
    }catch (StarStatusException e) {
      throw e;
    } 
    catch (Exception e) {
      logger.error(e.getMessage());
      throw new RuntimeException("getStar inner error:" + e.getMessage());
    }
    return orderExecution;
  }

  @Override
  public OrderExecution getOrderDetail(String orderNum,String userName) {
    OrderInfo orderInfo=orderInfoDao.queryByOrderNum(orderNum);
    List<OrderItem>orderItems=orderItemDao.queryByUserNameAndOrderNum(userName, orderNum);
    List<Product> products = new ArrayList<Product>();
    orderItems.forEach(x -> products.add(productDao.queryById(x.getProductId())));
    OrderExecution orderExecution=new OrderExecution(orderInfo, orderItems, OrderStatusEnum.stateOf(orderInfo.getStatus()));
    orderExecution.setProductList(products);
    return orderExecution;
  }

  @Override@Transactional(rollbackFor = RuntimeException.class)
  public int deleteOrder(String orderNum) {
    int index=0;
    try {
      //先删条目在删订单历史和订单表
      index+=orderItemDao.deleteOrderItemByOrderNum(orderNum);
      index+=orderHistoryDao.deleteByOrderNum(orderNum);
      index+=orderInfoDao.deleteByOrderNum(orderNum);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
   
    return index;
  }

}
