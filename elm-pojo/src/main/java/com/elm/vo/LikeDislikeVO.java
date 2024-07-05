package com.elm.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LikeDislikeVO {

    @ApiModelProperty("文章ID")
    private Long articleId;

    @ApiModelProperty("评论ID")
    private Long commentId;

    @ApiModelProperty("点赞数")
    private Integer likes;

    @ApiModelProperty("踩数")
    private Integer dislikes;
}
