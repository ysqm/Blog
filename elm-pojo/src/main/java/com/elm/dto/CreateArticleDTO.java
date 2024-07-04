package com.elm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(description = "创建文章时传递的数据模型")
public class CreateArticleDTO implements Serializable {

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章内容") // 将file改为content
    private String content;

    @ApiModelProperty("文章状态")
    private String status;

    @ApiModelProperty("文章标签ID列表")
    private List<Long> tagIds;
}