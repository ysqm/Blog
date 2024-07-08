package com.elm.controller.admin;

import com.elm.dto.LikeDislikeDTO;
import com.elm.result.Result;
import com.elm.entity.LikeDislike;
import com.elm.service.LikeDislikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likeDislike")
@Slf4j
@Api(tags = "点赞-踩相关接口")
public class LikeDislikeController {

    @Autowired
    private LikeDislikeService likeDislikeService;

    @PostMapping("/add")
    @ApiOperation("添加点赞操作")
    public Result<Void> addLikeDislike(@RequestBody LikeDislikeDTO likeDislikeDTO) {
        log.info("添加点赞操作：{}", likeDislikeDTO);
        likeDislikeService.addLikeDislike(toEntity(likeDislikeDTO));
        return Result.success();
    }

    @PostMapping("/remove")
    @ApiOperation("移除点赞操作")
    public Result<Void> removeLikeDislike(@RequestBody LikeDislikeDTO likeDislikeDTO) {
        log.info("移除点赞操作：{}", likeDislikeDTO);
        likeDislikeService.removeLikeDislike(toEntity(likeDislikeDTO));
        return Result.success();
    }

    @GetMapping("/count/comment/{commentId}")
    @ApiOperation("通过评论ID获取点赞")
    public Result<Integer> getLikeDislikeCountByCommentId(@PathVariable Integer commentId) {
        log.info("通过评论ID获取点赞数量：{}", commentId);
        return Result.success(likeDislikeService.getLikeDislikeCountByCommentId(commentId));
    }

    @GetMapping("/count/article/{articleId}")
    @ApiOperation("通过文章ID获取点赞数量")
    public Result<Integer> getLikeDislikeCountByArticleId(@PathVariable Integer articleId) {
        log.info("通过文章ID获取点赞数量：{}", articleId);
        return Result.success(likeDislikeService.getLikeDislikeCountByArticleId(articleId));
    }

    @GetMapping("/count/user/{userId}")
    @ApiOperation("通过用户ID获取所有评论的点赞数量")
    public Result<Integer> getLikeDislikeCountByUserId(@PathVariable Integer userId) {
        log.info("通过用户ID获取所有评论的点赞数量：{}", userId);
        return Result.success(likeDislikeService.getLikeDislikeCountByUserId(userId));
    }

    private LikeDislike toEntity(LikeDislikeDTO dto) {
        LikeDislike likeDislike = new LikeDislike();
        likeDislike.setUserId(dto.getUserId());
        likeDislike.setArticleId(dto.getArticleId());
        likeDislike.setCommentId(dto.getCommentId());
        likeDislike.setRecordStatus(dto.getRecordStatus());
        return likeDislike;
    }
}
