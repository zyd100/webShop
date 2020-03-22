package com.zl.webshop.dao;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;
import com.zl.webshop.entity.User;
import com.zl.webshop.entity.UserRoles;
/**
 * 
　 * <p>Title: UserRolesDaoTest</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月15日 </p>
 */
@Deprecated
public class UserRolesDaoTest extends BaseTest {
  @Autowired
  private UserRolesDao userRolesDao;
  @Autowired
  private UserDao userDao;

  @Test
  @Ignore
  public void testAddRole() {
    User user = userDao.queryById("admin");
    int count = userRolesDao.addRole(user.getUserName(), "ADMIN");
    System.out.println(count);
  }

  @Test
  @Ignore
  public void testUpdateRole() {
    User user = userDao.queryById("admin");
    int count = userRolesDao.updateRole(user.getUserName(), "CUSTOMER");
    System.out.println(count);
  }

  @Test
  @Ignore
  public void testDeleteRole() {
    User user = userDao.queryById("admin");
    int count = userRolesDao.deleteRole(user.getUserName());
    System.out.println(count);
  }

  @Test
  @Ignore
  public void testQueryAll() {
    List<UserRoles> list = userRolesDao.queryAll(0, 10);
    list.forEach(System.out::println);
  }

  @Test
  @Ignore
  public void testQueryByUserName() {
    User user = userDao.queryById("admin");
    UserRoles userRoles = userRolesDao.queryByUserName(user.getUserName());
    System.out.println(userRoles);
  }

  @Test
  @Ignore
  public void testQueryAllByRole() {
    List<UserRoles> list = userRolesDao.queryAllByRole("ADMIN", 0, 10);
    list.forEach(System.out::println);
  }
}
