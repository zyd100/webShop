/** 
　 * <p>Title: AdminCommentServiceTest.java</p> 
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
import com.zl.webshop.entity.Comment;
import com.zl.webshop.enums.CommentAuditEnum;

/** 
　 * <p>Title: AdminCommentServiceTest</p> 
　 * <p>Description: 管理评论测试</p> 
　 * @author zyd 
　 * <p>创建日期：2020年4月1日 </p>
*/
public class AdminCommentServiceTest extends BaseTest{

  @Autowired
  private AdminCommentService commentService;
  @Test@Ignore
  public void testGetComments() {
    commentService.getComments(0, 10).stream().forEach(System.out::println);
  }
  @Test@Ignore
  public void testDeleteComment() {
    Comment comment=new Comment();
    comment.setId(5);
    commentService.deleteComment(comment);
  }
  @Test@Ignore
  public void testUpdateCommentState() {
    commentService.updateCommentState(5, CommentAuditEnum.RATIFY);
  }
}
