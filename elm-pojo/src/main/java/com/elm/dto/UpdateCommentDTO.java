package com.elm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "更新评论时传递的数据模型")
public class UpdateCommentDTO implements Serializable {

    @ApiModelProperty("评论ID")
    private Long commentId;

    @ApiModelProperty("文章ID")
    private Long articleId;

    @ApiModelProperty("发布时间")
    private LocalDateTime commentDate;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("父评论ID")
    private Long parentCommentId;
}
