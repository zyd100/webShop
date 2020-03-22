/**
 * <p>
 * Title: ContactService.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月16日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.service;

import java.util.List;
import com.zl.webshop.entity.Contact;

/**
 * <p>
 * Title: ContactService
 * </p>
 * <p>
 * Description: 联系信息业务接口
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月16日
 *         </p>
 */
public interface ContactService {
  /**
   * 
   * <p>
   * Title: getDefaultContact
   * </p>
   * <p>
   * Description: 获取用户默认地址
   * </p>
   * 
   * @param userName 用户名
   * @return Contact
   */
  Contact getDefaultContact(String userName);

  /**
   * 
   * <p>
   * Title: getContacts
   * </p>
   * <p>
   * Description: 通过用户名获取联系信息
   * </p>
   * 
   * @param userName 用户名
   * @return 此用户的所有联系信息
   */
  List<Contact> getContacts(String userName);

  /**
   * 
   * <p>
   * Title: setDefaultContact
   * </p>
   * <p>
   * Description: 设置默认地址
   * </p>
   * 
   * @param contactId 地址序号
   * @param userName 用户名
   * @return 返回结果大于等于0表示设置成功
   */
  int setDefaultContact(long contactId, String userName);

  /**
   * 
   * <p>
   * Title: addContact
   * </p>
   * <p>
   * Description: 新增一个地址(状态默认设置为其他，防止冲突)
   * </p>
   * 
   * @param contact 联系信息
   * @return 大于等于0表示添加成功
   */
  int addContact(Contact contact);

  /**
   * 
   * <p>
   * Title: removeContact
   * </p>
   * <p>
   * Description: 删除一个联系地址
   * </p>
   * 
   * @param contact 联系信息
   * @return 大于等于0表示删除成功
   */
  int removeContact(Contact contact);

}
