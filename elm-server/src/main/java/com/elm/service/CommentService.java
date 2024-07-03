package com.elm.service;

import com.elm.dto.CreateCommentDTO;
import com.elm.dto.UpdateCommentDTO;
import com.elm.entity.Comment;
import com.elm.result.Result;
import com.elm.vo.CommentVO;

import java.util.List;

public interface CommentService {
    Result<CommentVO> addComment(CreateCommentDTO createCommentDTO);
    Comment getComment(Long commentId);
    Result<CommentVO> updateComment(UpdateCommentDTO updateCommentDTO);
    Result<Void> deleteComment(Long commentId);
    List<CommentVO> getCommentsByUserId(Long userId);
    List<CommentVO> getCommentsByArticleId(Long articleId);
    List<CommentVO> getCommentsByParentCommentId(Long parentCommentId);
}
