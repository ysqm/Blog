package com.elm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "社区文章展示数据模型")
public class ShowCommunityArticleDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章ID")
    private Integer id;

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("发布日期")
    private String publishDate;

    @ApiModelProperty("文章热度")
    private Integer heat;

    @ApiModelProperty("文章状态")
    private String status;

    @ApiModelProperty("文章内容预览")
    private String contentPreview;
}