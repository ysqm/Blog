package com.elm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(description = "创建标签时传递的数据模型")
public class CreateTagDTO implements Serializable {
    @ApiModelProperty("标签名称")
    private String tagName;
}
