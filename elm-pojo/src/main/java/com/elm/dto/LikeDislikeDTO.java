package com.elm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "点赞/踩操作的数据模型")
public class LikeDislikeDTO implements Serializable {

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("文章ID")
    private Long articleId;

    @ApiModelProperty("评论ID")
    private Long commentId;

    @ApiModelProperty("操作状态")
    private String recordStatus;
}
