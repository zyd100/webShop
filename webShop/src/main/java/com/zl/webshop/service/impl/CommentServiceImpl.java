/**
 * <p>
 * Title: CommentServiceImpl.java
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
package com.zl.webshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zl.webshop.dao.CommentDao;
import com.zl.webshop.dto.CommentExecution;
import com.zl.webshop.entity.Comment;
import com.zl.webshop.enums.CommentAuditEnum;
import com.zl.webshop.enums.CommentStarEnum;
import com.zl.webshop.service.CommentService;

/**
 * <p>
 * Title: CommentServiceImpl
 * </p>
 * <p>
 * Description: 评论业务接口实现
 * </p>
 * 
 * @author zyd
 *         <p>
 *         创建日期：2020年4月1日
 *         </p>
 */
@Service
public class CommentServiceImpl implements CommentService {
  private Logger logger = LoggerFactory.getLogger(getClass());
  @Autowired
  private CommentDao commentDao;

  @Override
  public List<CommentExecution> getcommentsByProductId(long productId, int offset, int limit) {
    List<Comment> comments = commentDao.queryByProductId(productId, offset, limit);
    List<CommentExecution> resultList = new ArrayList<CommentExecution>();
    comments.forEach(x -> resultList.add(new CommentExecution(x,
        CommentAuditEnum.stateOf(x.getAudit()), CommentStarEnum.stateOf(x.getStar()))));
    return resultList;
  }


  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public Comment addComment(long productId, String content, String userName, int star) {
    Comment result = null;
    try {
      result = new Comment();
      result.setStar(star);
      result.setContent(content);
      result.setUserName(userName);
      result.setProductId(productId);
      result.setAudit(CommentAuditEnum.AUDIT.getState());
      int index = commentDao.addComment(result);
      if (index < 1) {
        throw new RuntimeException("add comment error");
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
    return result;
  }

}
