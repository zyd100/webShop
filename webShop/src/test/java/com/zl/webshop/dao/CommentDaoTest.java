/** 
　 * <p>Title: CommentDaoTest.java</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月29日 </p>
　 * @version 1.0 
*/
package com.zl.webshop.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.zl.webshop.BaseTest;
import com.zl.webshop.entity.Comment;

/** 
　 * <p>Title: CommentDaoTest</p> 
　 * <p>Description: CommentDao 测试类</p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月29日 </p>
*/
public class CommentDaoTest extends BaseTest{

  @Autowired
  private CommentDao commentDao;
  
  @Test@Ignore
  public void testQueryAll() {
    System.out.println(commentDao.queryAll(0, 10));
  }
  @Test@Ignore
  public void testQueryByProductId() {
    System.out.println(commentDao.queryByProductId(7, 0, 10));
  }
  @Test@Ignore
  public void testQueryByUserName() {
    System.out.println(commentDao.queryByUserName("add", 0, 10));
  }
  @Test@Ignore
  public void testAddComment() {
    Comment comment=new Comment();
    comment.setContent("message");
    comment.setProductId(7);
    comment.setStar(5);
    comment.setUserName("bdd");
    commentDao.addComment(comment);
  }
  @Test@Ignore
  public void testDeleteCommentByProductId() {
    System.out.println(commentDao.deleteCommentByProductId(7));
  }
  @Test@Ignore
  public void testDeleteComment() {
    System.out.println(commentDao.deleteComment(1));
  }
  @Test@Ignore
  public void testDeleteCommentByUserName() {
    System.out.println(commentDao.deleteCommentByUserName("bdd"));
  }
  @Test@Ignore
  public void testUpdateComment() {
    Comment comment= commentDao.queryByUserName("add", 0, 10).get(0);
    comment.setContent(null);
    System.out.println(commentDao.updateComment(comment));
  }
  @Test@Ignore
  public void testQueryById() {
    System.out.println(commentDao.queryById(4));
  }
  @Test
  public void testCount() {
    System.out.println(commentDao.count());
  }
}
