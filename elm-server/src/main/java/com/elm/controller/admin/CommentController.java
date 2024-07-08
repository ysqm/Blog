package com.elm.controller.admin;

import com.elm.dto.CreateCommentDTO;
import com.elm.dto.UpdateCommentDTO;
import com.elm.result.Result;
import com.elm.service.CommentService;
import com.elm.vo.CommentVO;
import com.elm.entity.Comment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@Slf4j
@Api(tags = "评论相关接口")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    @ApiOperation("添加评论")
    public Result<CommentVO> addComment(@RequestBody CreateCommentDTO commentDTO) {
        if (commentDTO.getUserId() == null || commentDTO.getArticleId() == null) {
            log.info("User ID and Article ID are required");
        }
        log.info("新增评论：{}", commentDTO);
        return commentService.addComment(commentDTO);
    }

    @GetMapping("/get/{commentId}")
    @ApiOperation("获取评论详情")
    public Comment getComment(@PathVariable Long commentId) {
        log.info("获取评论：{}", commentId);
        return commentService.getComment(commentId);
    }

//    @PostMapping("/update")
//    @ApiOperation("更新评论")
//    public Result<CommentVO> updateComment(@RequestBody UpdateCommentDTO commentDTO) {
//        log.info("更新评论：{}", commentDTO);
//        return commentService.updateComment(commentDTO);
//    }

    @DeleteMapping("/delete/{commentId}")
    @ApiOperation("删除评论")
    public Result<Void> deleteComment(@PathVariable Long commentId) {
        log.info("删除评论：{}", commentId);
        return commentService.deleteComment(commentId);
    }

    @GetMapping("/user/{userId}")
    @ApiOperation("获取用户的所有评论")
    public Result<List<CommentVO>> getCommentsByUserId(@PathVariable Long userId) {
        log.info("获取用户的所有评论：{}", userId);
        return Result.success(commentService.getCommentsByUserId(userId));
    }

    @GetMapping("/article/{articleId}")
    @ApiOperation("获取文章的所有评论")
    public Result<List<CommentVO>> getCommentsByArticleId(@PathVariable Long articleId) {
        log.info("获取文章的所有评论：{}", articleId);
        return Result.success(commentService.getCommentsByArticleId(articleId));
    }

    @GetMapping("/parent/{parentCommentId}")
    @ApiOperation("获取父评论的所有子评论")
    public Result<List<CommentVO>> getCommentsByParentCommentId(@PathVariable Long parentCommentId) {
        log.info("获取父评论的所有子评论：{}", parentCommentId);
        return Result.success(commentService.getCommentsByParentCommentId(parentCommentId));
    }

//    @PostMapping("/reply")
//    @ApiOperation("回复评论")
//    public Result<CommentVO> replyComment(@RequestBody CreateCommentDTO commentDTO) {
//        log.info("回复评论：{}", commentDTO);
//        return commentService.addComment(commentDTO);
//    }
}
