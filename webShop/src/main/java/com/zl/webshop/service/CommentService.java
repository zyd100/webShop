/**
 * <p>
 * Title: CommentService.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年4月1日
 *         </p>
 * @version 1.0
 */
package com.zl.webshop.service;

import java.util.List;
import com.zl.webshop.dto.CommentExecution;
import com.zl.webshop.entity.Comment;

/**
 * <p>
 * Title: CommentService
 * </p>
 * <p>
 * Description: 评论业务接口
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年4月1日
 *         </p>
 */
public interface CommentService {
  /**
   * 
   * <p>
   * Title: getcommentsByProductId
   * </p>
   * <p>
   * Description: 根据产品序号获取评论
   * </p>
   * 
   * @param productId 产品序号
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 查询结果
   */
  List<CommentExecution> getcommentsByProductId(long productId, int offset, int limit);

  /**
   * 
   * <p>
   * Title: addComment
   * </p>
   * <p>
   * Description: 添加一个评论
   * </p>
   * 
   * @param productId 产品序号
   * @param content 评论内容
   * @param userName 用户名
   * @param star 好评度
   * @return 添加成功的评论
   */
  Comment addComment(long productId, String content, String userName, int star);
}
