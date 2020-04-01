/** 
　 * <p>Title: CommentServiceTest.java</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年4月1日 </p>
　 * @version 1.0 
*/
package com.zl.webshop.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;

/** 
　 * <p>Title: CommentServiceTest</p> 
　 * <p>Description: 评论业务测试</p> 
　 * @author zyd 
　 * <p>创建日期：2020年4月1日 </p>
*/
public class CommentServiceTest extends BaseTest{

  @Autowired
  private CommentService commentService;
  
  @Test@Ignore
  public void testAddComment() {
    commentService.addComment(7, "testcomment", "testId", 3);
  }
  @Test
  public void testGetcommentsByProductId() {
    System.out.println(commentService.getcommentsByProductId(7, 0, 10));
  }
}
