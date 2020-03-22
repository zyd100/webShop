/**
 * <p>
 * Title: ContactServiceImpl.java
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
package com.zl.webshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zl.webshop.dao.ContactDao;
import com.zl.webshop.entity.Contact;
import com.zl.webshop.enums.ContactStatusEnum;
import com.zl.webshop.exception.DeleteException;
import com.zl.webshop.exception.UpdateException;
import com.zl.webshop.service.ContactService;
import cn.hutool.core.util.StrUtil;

/**
 * <p>
 * Title: ContactServiceImpl
 * </p>
 * <p>
 * Description: ContactService接口实例
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月19日
 *         </p>
 */
@Service
public class ContactServiceImpl implements ContactService {
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private ContactDao contactDao;

  @Override
  public Contact getDefaultContact(String userName) {
    if (StrUtil.isEmpty(userName)) {
      // 判空
      return null;
    }
    Contact contact = null;
    contact = contactDao.queryByUserNameStatus(userName, ContactStatusEnum.DEFAULT.getState()).get(0);
    return contact;
  }

  @Override
  public List<Contact> getContacts(String userName) {
    if (StrUtil.isEmpty(userName)) {
      // 判空
      return null;
    }
    List<Contact> list = null;
    list = contactDao.queryByUserName(userName, 0, 10);
    return list;
  }

  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public int setDefaultContact(long contactId, String userName) {

    try {
      List<Contact> list = contactDao.queryByUserName(userName, 0, 1000);
      if(list.stream().filter(x->x.getId()==contactId).collect(Collectors.toList()).size()<1) {
        throw new UpdateException("no contact has been found");
      }
      // 遍历设置状态
      list.stream()
          .forEach(x -> x.setStatus(x.getId() == contactId ? ContactStatusEnum.DEFAULT.getState()
              : ContactStatusEnum.OTHER.getState()));
      // 遍历更新
      list.stream().forEach(x -> contactDao.updateContact(x));
      return list.size();
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new UpdateException("setDefaultContact inner error:"+e.getMessage());
    }

  }

  @Override
  public int addContact(Contact contact) {
    int count=0;
    try {
      contact.setStatus(ContactStatusEnum.OTHER.getState());
      count=contactDao.addContact(contact);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new UpdateException("addContact inner error:"+e.getMessage());
    }
   
    return count;
  }

  @Override
  public int removeContact(Contact contact) {
    int count=0;
    try {
      count=contactDao.deleteContact(contact);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new DeleteException(e.getMessage());
    }
    return count;
  }

}
