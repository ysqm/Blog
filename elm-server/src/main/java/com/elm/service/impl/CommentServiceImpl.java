package com.elm.service.impl;

import com.elm.constant.MessageConstant;
import com.elm.dto.CreateCommentDTO;
import com.elm.dto.UpdateCommentDTO;
import com.elm.entity.Comment;
import com.elm.mapper.CommentMapper;
import com.elm.result.Result;
import com.elm.service.CommentService;
import com.elm.vo.CommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Result<CommentVO> addComment(CreateCommentDTO createCommentDTO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(createCommentDTO, comment);
        comment.setCommentDate(LocalDateTime.now());
        if (createCommentDTO.getParentCommentId() == null || createCommentDTO.getParentCommentId() == 0) {
            comment.setParentCommentId(null);
        }
        commentMapper.createComment(comment);
        return Result.success(toVO(comment));
    }

    @Override
    public Comment getComment(Long commentId) {
        return commentMapper.getCommentById(commentId);
    }

    @Override
    public Result<CommentVO> updateComment(UpdateCommentDTO updateCommentDTO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(updateCommentDTO, comment);
        comment.setCommentDate(LocalDateTime.now());
        commentMapper.updateComment(comment);
        return Result.success(toVO(comment));
    }

    @Override
    public Result<Void> deleteComment(Long commentId) {
        commentMapper.deleteComment(commentId);
        return Result.success();
    }

    @Override
    public List<CommentVO> getCommentsByUserId(Long userId) {
        List<Comment> comments = commentMapper.getCommentsByUserId(userId);
        return comments.stream().map(this::toVO).collect(Collectors.toList());
    }

    @Override
    public List<CommentVO> getCommentsByArticleId(Long articleId) {
        List<Comment> comments = commentMapper.getCommentsByArticleId(articleId);
        return comments.stream().map(this::toVO).collect(Collectors.toList());
    }

    @Override
    public List<CommentVO> getCommentsByParentCommentId(Long parentCommentId) {
        List<Comment> comments = commentMapper.getCommentsByParentCommentId(parentCommentId);
        return comments.stream().map(this::toVO).collect(Collectors.toList());
    }

    private CommentVO toVO(Comment comment) {
        CommentVO vo = new CommentVO();
        vo.setCommentId(comment.getCommentId());
        vo.setUserId(comment.getUserId());
        vo.setArticleId(comment.getArticleId());
        vo.setParentCommentId(comment.getParentCommentId());
        vo.setContent(comment.getContent());
        vo.setCommentDate(comment.getCommentDate());
        return vo;
    }
}
