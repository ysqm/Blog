package com.elm.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LikeDislikeVO {

    @ApiModelProperty("目标ID(comment/article)")
    private Long targetId;

    @ApiModelProperty("点赞数")
    private Integer likes;

    @ApiModelProperty("踩数")
    private Integer dislikes;


}
