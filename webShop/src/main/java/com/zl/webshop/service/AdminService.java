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

import java.util.List;
import com.zl.webshop.dto.OrderExecution;
import com.zl.webshop.dto.ProductExecution;
import com.zl.webshop.dto.UserExecution;
import com.zl.webshop.entity.Contact;
import com.zl.webshop.entity.OrderHistory;
import com.zl.webshop.entity.Product;
import com.zl.webshop.entity.ProductCategory;
import com.zl.webshop.entity.User;
import com.zl.webshop.enums.OrderStatusEnum;
import com.zl.webshop.enums.UserRolesEnum;

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
   * Title: getCategories
   * </p>
   * <p>
   * Description:
   * </p>
   * 
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 分类列表
   */
  List<ProductCategory> getCategories(int offset, int limit);

  /**
   * 
   * <p>
   * Title: addProductCategory
   * </p>
   * <p>
   * Description: 添加一个新的分类
   * </p>
   * 
   * @param category 产品类别
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int addProductCategory(ProductCategory category);

  /**
   * 
   * <p>
   * Title: deleteProductCategory
   * </p>
   * <p>
   * Description: 删除一个分类，同时原本此类别的产品会变成其他分类
   * </p>
   * 
   * @param category 类别
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteProductCategory(ProductCategory category);

  /**
   * 
   * <p>
   * Title: updateProductCategory
   * </p>
   * <p>
   * Description: 更新一个类别
   * </p>
   * 
   * @param category 类别
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int updateProductCategory(ProductCategory category);

  /**
   * 
   * <p>
   * Title: getProductsByCategory
   * </p>
   * <p>
   * Description: 根据分类获取产品
   * </p>
   * 
   * @param categoryId 产品类别序号
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return ProductExecution
   */
  ProductExecution getProductsByCategory(int categoryId, int offset, int limit);

  /**
   * 
   * <p>
   * Title: getProducts
   * </p>
   * <p>
   * Description: 获取产品列表
   * </p>
   * 
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return ProductExecution
   */
  ProductExecution getProducts(int offset, int limit);

  /**
   * 
   * <p>
   * Title: getProduct
   * </p>
   * <p>
   * Description: 根据产品序号获得一个产品的相关信息
   * </p>
   * 
   * @param productId 产品序号
   * @return ProductExecution
   */
  ProductExecution getProduct(long productId);

  /**
   * 
   * <p>
   * Title: updateProduct
   * </p>
   * <p>
   * Description: 更新一个或多个产品
   * </p>
   * 
   * @param products 产品列表
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int updateProduct(Product... products);

  /**
   * 
   * <p>
   * Title: deleteProduct
   * </p>
   * <p>
   * Description: 删除一个或多个产品
   * </p>
   * 
   * @param products 产品列表
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteProduct(Product... products);

  /**
   * 
   * <p>
   * Title: addProduct
   * </p>
   * <p>
   * Description: 添加一个或多个产品
   * </p>
   * 
   * @param products 产品列表
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int addProduct(Product... products);

  /**
   * 
   * <p>
   * Title: getUsers
   * </p>
   * <p>
   * Description: 获取用户
   * </p>
   * 
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return UserExecution
   */
  UserExecution getUsers(int offset, int limit);

  /**
   * 
   * <p>
   * Title: getUsersByRole
   * </p>
   * <p>
   * Description: 根据权限获取用户
   * </p>
   * 
   * @param role 用户权限
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return UserExecution
   */
  UserExecution getUsersByRole(UserRolesEnum role, int offset, int limit);

  /**
   * 
   * <p>
   * Title: getUser
   * </p>
   * <p>
   * Description: 根据用户名获取用户相关信息
   * </p>
   * 
   * @param userName 用户名
   * @return UserExecution
   */
  UserExecution getUser(String userName);

  /**
   * 
   * <p>
   * Title: updateUser
   * </p>
   * <p>
   * Description: 更新一个或多个用户信息
   * </p>
   * 
   * @param users 用户列表 
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int updateUser(User... users);

  /**
   * 
   * <p>
   * Title: deleteUser
   * </p>
   * <p>
   * Description: 删除一个或多个用户信息
   * </p>
   * 
   * @param users 用户列表
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteUser(User... users);

  /**
   * 
   * <p>
   * Title: addUser
   * </p>
   * <p>
   * Description: 添加一个或多个用户信息
   * </p>
   * 
   * @param users 用户列表
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int addUser(User... users);

  /**
   * 
   * <p>
   * Title: getOrderInfo
   * </p>
   * <p>
   * Description: 根据用户名和订单编号获取此订单相关信息
   * </p>
   * 
   * @param userName 用户名
   * @param orderNum 订单编号
   * @return OrderExecution
   */
  OrderExecution getOrderInfo(String userName, String orderNum);

  /**
   * 
   * <p>
   * Title: getOrderItem
   * </p>
   * <p>
   * Description: 根据用户名和订单编号获取订单条目
   * </p>
   * 
   * @param userName 用户名
   * @param orderNum 订单编号
   * @return OrderExecution
   */
  OrderExecution getOrderItem(String userName, String orderNum);

  /**
   * 
   * <p>
   * Title: getOrderInfoIem
   * </p>
   * <p>
   * Description: 根据用户名获取订单信息和订单条目信息
   * </p>
   * 
   * @param userName 用户名
   * @param orderNum 订单编号
   * @return OrderExecution
   */
  OrderExecution getOrderInfoIem(String userName, String orderNum);

  /**
   * 
   * <p>
   * Title: getOrderHistories
   * </p>
   * <p>
   * Description: 获取订单历史
   * </p>
   * 
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 订单历史列表
   */
  List<OrderHistory> getOrderHistories(int offset, int limit);

  /**
   * 
   * <p>
   * Title: updateOrderStatus
   * </p>
   * <p>
   * Description: 更新订单状态
   * </p>
   * 
   * @param orderNum 订单编号
   * @param status 订单状态
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int updateOrderStatus(String orderNum, OrderStatusEnum status);

  /**
   * 
   * <p>
   * Title: updateOrderItem
   * </p>
   * <p>
   * Description: 更新有关于这个产品的订单条目信息,如果更新了价格，那会把订单信息的总金额作更新
   * </p>
   * 
   * @param product 产品
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int updateOrderItem(Product product);

  /**
   * 
   * <p>
   * Title: deleteOrderInfo
   * </p>
   * <p>
   * Description:删除一个订单
   * </p>
   * 
   * @param orderNum 订单编号
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteOrderInfo(String orderNum);

  /**
   * 
   * <p>
   * Title: deleteOrderItem
   * </p>
   * <p>
   * Description: 删除此订单编号下的所有订单条目
   * </p>
   * 
   * @param orderNum 订单条目
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteOrderItem(String orderNum);

  /**
   * 
   * <p>
   * Title: deleteOrderHistory
   * </p>
   * <p>
   * Description: 根据订单编号删除订单历史
   * </p>
   * 
   * @param orderNum 订单编号
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteOrderHistory(String orderNum);

  /**
   * 
   * <p>
   * Title: addOrderHistory
   * </p>
   * <p>
   * Description: 添加一个订单历史
   * </p>
   * 
   * @param orderHistory 订单历史
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int addOrderHistory(OrderHistory orderHistory);

  /**
   * 
   * <p>
   * Title: getContacts
   * </p>
   * <p>
   * Description: 获取所有联系信息
   * </p>
   * 
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 联系信息列表
   */
  List<Contact> getContacts(int offset, int limit);

  /**
   * 
   * <p>
   * Title: updateContact
   * </p>
   * <p>
   * Description: 更新一个或多个联系信息
   * </p>
   * 
   * @param contacts 联系信息列表
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int updateContact(Contact... contacts);

  /**
   * 
   * <p>
   * Title: deleteContact
   * </p>
   * <p>
   * Description: 删除一个或多个联系信息
   * </p>
   * 
   * @param contacts 联系信息列表
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteContact(Contact... contacts);

  /**
   * 
   * <p>
   * Title: addContact
   * </p>
   * <p>
   * Description: 添加一个或多个联系信息
   * </p>
   * 
   * @param contacts 联系信息列表
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int addContact(Contact... contacts);
}
