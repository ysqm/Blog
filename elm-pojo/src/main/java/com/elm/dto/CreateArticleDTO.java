package com.elm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@ApiModel(description = "创建文章时传递的数据模型")
public class CreateArticleDTO implements Serializable {

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章文件")
    private MultipartFile file;

    @ApiModelProperty("文章状态")
    private String status;
}