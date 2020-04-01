/** 
　 * <p>Title: AdminCommentService.java</p> 
　 * <p>Description: </p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月30日 </p>
　 * @version 1.0 
*/
package com.zl.webshop.service;

import java.util.List;
import com.zl.webshop.dto.CommentExecution;
import com.zl.webshop.entity.Comment;
import com.zl.webshop.enums.CommentAuditEnum;

/** 
　 * <p>Title: AdminCommentService</p> 
　 * <p>Description: 管理员评论业务接口</p> 
　 * @author zyd 
　 * <p>创建日期：2020年3月30日 </p>
*/
public interface AdminCommentService {
  
  /**
   * 
  *<p>Title: getComments</p> 
  *<p>Description: </p> 
  　 * @param offset 查询起始位置
  　 * @param limit 查询条数
  　 * @return 评论列表
   */
  List<CommentExecution>getComments(int offset,int limit);
  /**
   * 
  *<p>Title: deleteComment</p> 
  *<p>Description: 删除一个评论</p> 
  　 * @param comment 评论
  　 * @return 最新的评论表总数
   */
  int deleteComment(Comment comment);
  /**
   * 
  *<p>Title: updateCommentState</p> 
  *<p>Description: 更新评论状态</p> 
  　 * @param id 评论id
  　 * @param auditEnum 评论状态
  　 * @return 返回更新后的评论
   */
  CommentExecution updateCommentState(long id,CommentAuditEnum auditEnum);
}
