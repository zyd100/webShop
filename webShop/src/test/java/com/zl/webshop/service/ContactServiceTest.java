/**
 * <p>
 * Title: ContactServiceTest.java
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

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;
import com.zl.webshop.entity.Contact;

/**
 * <p>
 * Title: ContactServiceTest
 * </p>
 * <p>
 * Description: ContactService测试例
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月19日
 *         </p>
 */
public class ContactServiceTest extends BaseTest {

  @Autowired
  private ContactService contactService;

  @Test@Ignore
  public void testGetDefaultContact() {
    String userName="testId";
    Contact contact=contactService.getDefaultContact(userName);
    System.out.println(contact);
  }

  @Test@Ignore
  public void testGetContacts() {
    String userName="testId";
    contactService.getContacts(userName).stream().forEach(System.out::println);
  }

  @Test@Ignore
  public void testSetDefaultContact() {
    String userName="testId";
    contactService.setDefaultContact(4, userName);
  }

  @Test@Ignore
  public void testAddContact() {
    String userName="admin";
    Contact contact =new Contact();
    contact.setUserName(userName);
    contact.setContactAddress("beijing");
    contactService.addContact(contact);
  }

  @Test@Ignore
  public void testRemoveContact() {
    Contact contact=new Contact();
    contact.setUserName("admin");
    contact.setId(7);
     int count= contactService.removeContact(contact);
     System.out.println(count);
  }
}
