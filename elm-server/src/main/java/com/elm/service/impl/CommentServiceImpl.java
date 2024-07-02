package com.elm.service.impl;

import com.elm.constant.MessageConstant;
import com.elm.dto.CreateCommentDTO;
import com.elm.dto.UpdateCommentDTO;
import com.elm.entity.Article;
import com.elm.entity.Comment;
import com.elm.mapper.CommentMapper;
import com.elm.result.Result;
import com.elm.service.CommentService;
import com.elm.vo.CommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

//    private final CommentMapper commentMapper;
//
//    public CommentServiceImpl(CommentMapper commentMapper) {
//        this.commentMapper = commentMapper;
//    }
    @Autowired
    private  CommentMapper commentMapper;

    @Override
    public CommentVO addComment(CreateCommentDTO createCommentDTO) {

        Comment comment = new Comment();
        BeanUtils.copyProperties(createCommentDTO, comment);
        comment.setUserId(createCommentDTO.getUserId());
        comment.setCommentDate(LocalDateTime.now());
        comment.setArticleId(createCommentDTO.getArticleId());
        comment.setContent(createCommentDTO.getContent());
        if (createCommentDTO.getParentCommentId() == null || createCommentDTO.getParentCommentId() == 0) {
            comment.setParentCommentId(null); // 顶级评论设置为 null
        }else{
            comment.setParentCommentId(createCommentDTO.getParentCommentId());
        }

        commentMapper.createComment(comment);
        return toVO(comment);
    }

    @Override
    public Comment getComment(Integer commentId) {
        return commentMapper.getCommentById(commentId);
    }

    @Override
    public CommentVO updateComment(UpdateCommentDTO updateCommentDTO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(updateCommentDTO, comment);
        comment.setCommentDate(LocalDateTime.now());
        commentMapper.updateComment(comment);
        return toVO(comment);
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
