package com.elm.mapper;

import com.elm.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 获取所有评论

    List<Comment> getAllComments();

    // 根据评论ID获取评论

    Comment getCommentById(long commentId);

    // 根据文章ID获取评论

    List<Comment> getCommentsByArticleId(long articleId);

    // 根据用户ID获取评论

    List<Comment> getCommentsByUserId(long userId);

    // 创建评论

    Integer createComment(Comment comment);

    // 更新评论

    Integer updateComment(Comment comment);

    // 删除评论

    Integer deleteComment(long commentId);
}
