package com.zl.webshop.dao;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;
import com.zl.webshop.entity.Contact;
/**
 * 
　 * <p>Title: ContactDaoTest</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月15日 </p>
 */
@Deprecated
public class ContactDaoTest extends BaseTest{
  @Autowired
  private ContactDao contactDao;
  
  @Test@Ignore
  public void testAddContact() {
    Contact contact=new Contact();
    contact.setContactAddress("北京市");
    contact.setContactMobile("13456");
    contact.setContactName("zz");
    contact.setUserName("admin");
    int count =contactDao.addContact(contact);
    System.out.println(count);
  }
  @Test@Ignore
  public void testQueryAll() {
    List<Contact>list=contactDao.queryAll(0, 10);
    System.out.println(list);
  }
  @Test@Ignore
  public void testQueryById() {
    Contact contact=contactDao.queryById(1);
    System.out.println(contact);
  }
  @Test @Ignore
  public void testQueryByUserName() {
    List<Contact>list=contactDao.queryByUserName("test", 0, 10);
    System.out.println(list);
  }
  @Test @Ignore
  public void testUpdateContact() {
    Contact contact=contactDao.queryById(1);
    contact.setContactName("dd");
    int count=contactDao.updateContact(contact);
    System.out.println(count);
  }
  @Test@Ignore
  public void testDeleteContact() {
    Contact contact=contactDao.queryById(2);
    int count=contactDao.deleteContact(contact);
    System.out.println(count);
  }
  @Test@Ignore
  public void testDeleteContactByUserName() {
    String userName="testId";
    int count=contactDao.deleteContactByUserName(userName);
    System.out.println(count);
  }
}
