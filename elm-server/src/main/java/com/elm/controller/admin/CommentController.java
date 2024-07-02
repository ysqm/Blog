package com.elm.controller.admin;

import com.elm.dto.CreateCommentDTO;
import com.elm.dto.UpdateCommentDTO;
import com.elm.entity.Comment;
import com.elm.result.Result;
import com.elm.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Slf4j
@Api(tags = "评论相关接口")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    @ApiOperation("添加评论")
    public Result addComment(@RequestBody CreateCommentDTO commentDTO) {
        //return commentService.addComment(commentDTO);
        return Result.success(commentDTO);
    }

    @GetMapping("/get/{id}")
    @ApiOperation("获取评论详情")
    public Comment getComment(@PathVariable Integer id) {
        return commentService.getComment(id);
    }

    @PostMapping("/update")
    @ApiOperation("更新评论")
    public Result updateComment(@RequestBody UpdateCommentDTO commentDTO) {
        //return commentService.updateComment(commentDTO);
        return Result.success(commentDTO);
    }
}
