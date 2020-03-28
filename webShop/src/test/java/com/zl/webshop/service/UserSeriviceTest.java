/**
 * <p>
 * Title: UserSeriviceTest.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月18日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;
import com.zl.webshop.entity.User;

/**
 * <p>
 * Title: UserSeriviceTest
 * </p>
 * <p>
 * Description: UserSerive测试
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月18日
 *         </p>
 */
public class UserSeriviceTest extends BaseTest {

  @Autowired
  private UserService userService;

  @Test@Ignore
  public void testRegister() {
    User user = new User();
    user.setUserName("testId");
    user.setPassword("123456");
    userService.register(user);
  }
  @Test
public void testgerBasicInfo() {
  System.out.println(userService.getBasicInfo("testId"));
}
  @Test@Ignore
  public void testLogin() {
    User user=new User();
    user.setUserName("customRegister");
    user.setPassword("123456");
    System.out.println(userService.login(user));
  }

  @Test@Ignore
  public void testDeleteAccount() {
    User user=new User();
    user.setUserName("customRegister");
    user.setPassword("666666");
    System.out.println(userService.deleteAccount(user));
  }

  @Test@Ignore
  public void testUpdateUserInfo() {
    User user=new User();
    user.setUserName("customRegister");
    user.setPassword("666666");
    user.setNickName("nicko");
    System.out.println(userService.updateUserInfo(user));
  }
}
