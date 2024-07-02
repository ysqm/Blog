package com.elm.controller.admin;

import com.elm.dto.CreateArticleDTO;
import com.elm.dto.CreateCommentDTO;
import com.elm.dto.UpdateCommentDTO;
import com.elm.entity.Comment;
import com.elm.vo.ArticleVO;
import com.elm.vo.CommentVO;
import com.elm.result.Result;
import com.elm.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;

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
        /*CreateCommentDTO dto = new CreateCommentDTO();
        dto.setUserId(userId);
        dto.setArticleId(articleId);
        dto.setContent(content);
        dto.setParentCommentId(parentCommentId);


        @RequestParam("userId") Long userId,
                    @RequestParam("articleId") Long articleId,
                    @RequestParam("content") String content,
                    @RequestPart("parentCommentId") Long parentCommentId
         */
        log.info("新增评论：{}", commentDTO);
        CommentVO commentVO = commentService.addComment(commentDTO);
        return Result.success(commentVO);

    }

    @GetMapping("/get/{commentid}")
    @ApiOperation("获取评论详情")
    public Comment getComment(@PathVariable Integer commentid) {
        log.info("评论内容：{}", commentid);
        return commentService.getComment(commentid);
    }

    @PostMapping("/update")
    @ApiOperation("更新评论")
    public Result<CommentVO> updateComment(@RequestBody UpdateCommentDTO commentDTO) {
        log.info("评论内容：{}", commentDTO);
        CommentVO commentVO = commentService.updateComment(commentDTO);
        return Result.success(commentVO);
    }
}
