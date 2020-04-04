/** 
　 * <p>Title: FileServiceTest.java</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年4月3日 </p>
　 * @version 1.0 
*/
package com.zl.webshop.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;

/** 
　 * <p>Title: FileServiceImpl</p> 
　 * <p>Description: 文件上传业务测试</p> 
　 * @author zyd 
　 * <p>创建日期：2020年4月3日 </p>
*/
public class FileServiceTest extends BaseTest{
  
@Autowired
private FileService fileService;
@Test
public void testUpload() {

 System.out.println(System.getProperty("user.dir")+"\\src\\main\\webapp\\resources\\images\\");
}
@Test
public void testDelete() {
  System.out.println(fileService.deleteFile("a.txt"));
}
}
