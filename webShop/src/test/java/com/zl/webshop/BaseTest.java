package com.zl.webshop;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * 
　 * <p>Title: BaseTest</p> 
　 * <p>Description: </p> 
　 * @author 李奕锋 
　 * <p>创建日期：2020年3月15日 </p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class BaseTest {

}
