package com.elm.mapper;

import com.elm.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> getAllComments();

    Comment getCommentById(Long commentId);

    List<Comment> getCommentsByArticleId(Long articleId);

    List<Comment> getCommentsByUserId(Long userId);

    List<Comment> getCommentsByParentCommentId(Long parentCommentId);

    Integer createComment(Comment comment);

    Integer updateComment(Comment comment);

    Integer deleteComment(Long commentId);
}
