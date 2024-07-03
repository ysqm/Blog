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

    @ApiModelProperty("目标ID(comment/article)")
    private Long targetId;

    @ApiModelProperty("操作类型")
    private String recordType;

    @ApiModelProperty("操作状态")
    private String recordStatus;
}
