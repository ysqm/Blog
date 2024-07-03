package com.elm.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ApiOperation("用户登录返回值")
public class CommentVO {
    @ApiModelProperty("评论ID")
    private Long commentId;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("文章ID")
    private Long articleId;

    @ApiModelProperty("父评论ID")
    private Long parentCommentId;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("评论时间")
    private LocalDateTime commentDate;

}
