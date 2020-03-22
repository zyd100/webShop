/**
 * <p>
 * Title: ContactDao.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 * @date 2020年3月13日
 * @version 1.0
 */
package com.zl.webshop.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.zl.webshop.entity.Contact;

/**
 * <p>
 * Title: ContactDao
 * </p>
 * <p>
 * Description: 联系信息表Dao
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月13日
 *         </p>
 */
public interface ContactDao {
  /**
   * 
   * <p>
   * Title: queryByUserNameStatus
   * </p>
   * <p>
   * Description: 根据用户名和联系信息表的状态属性获取联系信息
   * </p>
   * 
   * @param userName 用户名
   * @param status 状态属性
   * @return 联系列表
   */
  List<Contact> queryByUserNameStatus(@Param("userName") String userName, @Param("status") int status);

  /**
   * 
   * <p>
   * Title: queryAll
   * </p>
   * <p>
   * Description: 查询所有联系信息
   * </p>
   * 
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 联系列表
   */
  List<Contact> queryAll(@Param("offset") int offset, @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: queryByUserName
   * </p>
   * <p>
   * Description: 通过用户名查询联系信息
   * </p>
   * 
   * @param userName 用户名
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 联系列表
   */
  List<Contact> queryByUserName(@Param("userName") String userName, @Param("offset") int offset,
      @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: queryById
   * </p>
   * <p>
   * Description: 使用id查询单个联系信息
   * </p>
   * 
   * @param id 序号
   * @return 联系信息对象
   */
  Contact queryById(@Param("id") long id);

  /**
   * 
   * <p>
   * Title: addContact
   * </p>
   * <p>
   * Description: 添加一个联系信息
   * </p>
   * 
   * @param contact 联系信息对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int addContact(@Param("contact") Contact contact);

  /**
   * 
   * <p>
   * Title: updateContact
   * </p>
   * <p>
   * Description: 更新一个联系信息
   * </p>
   * 
   * @param contact 联系信息对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int updateContact(@Param("contact") Contact contact);

  /**
   * 
   * <p>
   * Title: deleteContact
   * </p>
   * <p>
   * Description: 删除一个联系信息
   * </p>
   * 
   * @param contact 联系信息对象
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteContact(@Param("contact") Contact contact);

  /**
   * 
   * <p>
   * Title: deleteContactByUserName
   * </p>
   * <p>
   * Description: 根据用户名删除联系信息
   * </p>
   * 
   * @param userName 用户名
   * @return 如果影响行数等于大于1 表示更新的记录行数
   */
  int deleteContactByUserName(@Param("userName") String userName);
}
