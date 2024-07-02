package com.elm.service.impl;

import com.elm.constant.MessageConstant;
import com.elm.dto.CreateCommentDTO;
import com.elm.dto.UpdateCommentDTO;
import com.elm.entity.Comment;
import com.elm.mapper.CommentMapper;
import com.elm.result.Result;
import com.elm.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public Result addComment(CreateCommentDTO createCommentDTO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(createCommentDTO, comment);
        comment.setCommentDate(LocalDateTime.now());

        // Check for null or zero parentCommentId
        if (createCommentDTO.getParentCommentId() == null || createCommentDTO.getParentCommentId() == 0) {
            comment.setParentCommentId(null); // 顶级评论设置为 null
        } else {
            // Verify that the parent comment exists
            Comment parentComment = commentMapper.getCommentById(createCommentDTO.getParentCommentId());
            if (parentComment == null) {
                return Result.error(MessageConstant.COMMENT_CREATION_FAILED);
            }
            comment.setParentCommentId(createCommentDTO.getParentCommentId());
        }

        int msg = commentMapper.createComment(comment);
        if (msg > 0) {
            return Result.success();
        } else {
            return Result.error(MessageConstant.COMMENT_CREATION_FAILED);
        }
    }

    @Override
    public Comment getComment(Integer commentId) {
        return commentMapper.getCommentById(commentId);
    }

    @Override
    public Result updateComment(UpdateCommentDTO updateCommentDTO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(updateCommentDTO, comment);
        comment.setCommentDate(LocalDateTime.now());
        int msg = commentMapper.updateComment(comment);
        if (msg > 0) {
            return Result.success();
        } else {
            return Result.error(MessageConstant.COMMENT_UPDATE_FAILED);
        }
    }
}
