/** 
　 * <p>Title: UserDaoTest.java</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * @date 2020年3月12日 
　 * @version 1.0 
*/
package com.zl.webshop.dao;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;
import com.zl.webshop.entity.User;

/** 
　 * <p>Title: UserDaoTest</p> 
　 * <p>Description: UserDao测试类</p> 
　 * @author zyd 
　 * @date 2020年3月12日 
*/
@Deprecated
public class UserDaoTest extends BaseTest{
  @Autowired
  private UserDao userDao;
  @Test@Ignore
  public void testCount() {
    int count=userDao.count();
    System.out.println(count);
  }
  @Test@Ignore
  public void testQueryById() {
    User user=userDao.queryByUserName("admin");
    System.out.println(user);
  }
  @Test@Ignore
  public void testQueryAll() {
    List<User>list=userDao.queryAll(0, 10);
    System.out.println(list);
  }
  @Test@Ignore
  public void testAddUser() {
    User user=new User();
    //user.setEmail("1019661738@qq.com");
    //user.setNickName("ddd");
    user.setUserName("testimage");
    user.setPassword("123456");
    user.setImage("image");
    int count=userDao.addUser(user);
    System.out.println(count);
  }
  
  @Test@Ignore
  public void testUpdateUser() {
   /* User user=userDao.queryByUserName("zydadmin");
    user.setEmail(null);
    int count=userDao.updateUser(user);
    System.out.println(count);*/
    User user=new User();
    user.setUserName("testimage");
    user.setImage("23");
    //user.setNickName("admin");
    userDao.updateUser(user);
  }
  @Test@Ignore
  public void testDeleteUser() {
    User user=userDao.queryByUserName("zydadmin");
    int count=userDao.deleteUser(user);
    System.out.println(count);
  }
  @Test@Ignore
  public void testFuzzyCount() {
   System.out.println(userDao.fuzzyCount("d"));
  }
  @Test@Ignore
  public void testFuzzyQuuery() {
    System.out.println(userDao.fuzzyQueryAllByText("d", 0,10));
  }
  @Test
  public void testUpdateImage() {
    userDao.updateUserImage("admin", "abc.jpg");
  }
}
