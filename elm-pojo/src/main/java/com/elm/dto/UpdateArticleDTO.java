package com.elm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(description = "更新文章时传递的数据模型")
public class UpdateArticleDTO implements Serializable {

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章文件")
    private MultipartFile file;

    @ApiModelProperty("文章状态")
    private String status;

    @ApiModelProperty("文章标签ID列表")
    private List<Long> tagIds;
}
