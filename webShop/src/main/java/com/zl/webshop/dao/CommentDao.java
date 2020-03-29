package com.zl.webshop.dao;
/**
 * 
 * <p>
 * Title: CommentDao
 * </p>
 * <p>
 * Description:评论表Dao
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年3月29日
 *         </p>
 */

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.zl.webshop.entity.Comment;

public interface CommentDao {
  /**
   * 
   * <p>
   * Title: queryAll
   * </p>
   * <p>
   * Description: 查询所有评论
   * </p>
   * 
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 评论列表
   */
  List<Comment> queryAll(@Param("offset") int offset, @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: queryByProductId
   * </p>
   * <p>
   * Description: 根据产品序号获取评论
   * </p>
   * 
   * @param productId 产品序号
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 评论列表
   */
  List<Comment> queryByProductId(@Param("productId") long productId, @Param("offset") int offset,
      @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: queryByUserName
   * </p>
   * <p>
   * Description: 根据用户名获得评论
   * </p>
   * 
   * @param userName 用户名
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 评论列表
   */
  List<Comment> queryByUserName(@Param("userName") String userName, @Param("offset") int offset,
      @Param("limit") int limit);

  /**
   * 
   * <p>
   * Title: queryById
   * </p>
   * <p>
   * Description: 根据序号获取评论
   * </p>
   * 
   * @param id 评论序号
   * @return Comment
   */
  Comment queryById(@Param("id") long id);

  /**
   * 
   * <p>
   * Title: addComment
   * </p>
   * <p>
   * Description: 新增一个评论
   * </p>
   * 
   * @param comment 评论对象
   * @return 大于等于1表示新增成功
   */
  int addComment(@Param("comment") Comment comment);

  /**
   * 
   * <p>
   * Title: deleteCommentByProductId
   * </p>
   * <p>
   * Description: 根据产品序号删除评论
   * </p>
   * 
   * @param productId 产品序号
   * @return 大于等于1表示删除成功
   */
  int deleteCommentByProductId(@Param("productId") long productId);

  /**
   * 
   * <p>
   * Title: deleteComment
   * </p>
   * <p>
   * Description: 删除评论
   * </p>
   * 
   * @param id 评论序号
   * @return 大于等于1表示删除成功
   */
  int deleteComment(@Param("id") long id);

  /**
   * 
   * <p>
   * Title: deleteCommentByUserName
   * </p>
   * <p>
   * Description: 根据用户名删除评论
   * </p>
   * 
   * @param userName 用户名
   * @return 大于等于1表示删除成功
   */
  int deleteCommentByUserName(@Param("userName") String userName);

  /**
   * 
   * <p>
   * Title: updateComment
   * </p>
   * <p>
   * Description: 更新评论仅支持更新评论内容和评论好评度
   * </p>
   * 
   * @param comment 评论对象
   * @return 大于等于1表示更新成功
   */
  int updateComment(@Param("comment") Comment comment);
}
