/**
 * <p>
 * Title: ContactExecution.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月20日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.dto;

import java.util.List;
import com.zl.webshop.entity.Contact;
import com.zl.webshop.enums.ContactStatusEnum;

/**
 * <p>
 * Title: ContactExecution
 * </p>
 * <p>
 * Description: 联系信息DTO
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月20日
 *         </p>
 */
public class ContactExecution {
  /**
   * 用户名
   */
  private String userName;
  /**
   * 联系信息
   */
  private Contact contact;
  /**
   * 联系信息列表
   */
  private List<Contact> contacts;
  /**
   * 联系信息状态
   */
  private int state;
  /**
   * 联系信息状态的信息
   */
  private String stateInfo;

  /**
   * 
   * <p>
   * Title:
   * </p>
   * <p>
   * Description: 一个用户的所有联系信息的构造器
   * </p>
   * 
   * @param userName 用户名
   * @param contacts 联系信息列表
   */
  public ContactExecution(String userName, List<Contact> contacts) {
    setUserName(userName);
    setContacts(contacts);
  }

  /**
   * 
   * <p>
   * Title:
   * </p>
   * <p>
   * Description: 单个联系信息的构造器
   * </p>
   * 
   * @param userName 用户名
   * @param contact 联系信息
   * @param status 联系信息的状态
   */
  public ContactExecution(String userName, Contact contact, ContactStatusEnum status) {
    setUserName(userName);
    setContact(contact);
    setState(status.getState());
    setStateInfo(status.getStateInfo());
  }

  /**
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * @return the contact
   */
  public Contact getContact() {
    return contact;
  }

  /**
   * @param contact the contact to set
   */
  public void setContact(Contact contact) {
    this.contact = contact;
  }

  /**
   * @return the contacts
   */
  public List<Contact> getContacts() {
    return contacts;
  }

  /**
   * @param contacts the contacts to set
   */
  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  /**
   * @return the state
   */
  public int getState() {
    return state;
  }

  /**
   * @param state the state to set
   */
  public void setState(int state) {
    this.state = state;
  }

  /**
   * @return the stateInfo
   */
  public String getStateInfo() {
    return stateInfo;
  }

  /**
   * @param stateInfo the stateInfo to set
   */
  public void setStateInfo(String stateInfo) {
    this.stateInfo = stateInfo;
  }

}
