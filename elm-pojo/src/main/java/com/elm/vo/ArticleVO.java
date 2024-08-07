package com.elm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "返回的文章数据模型")
public class ArticleVO {

    @ApiModelProperty("文章ID")
    private Long articleId;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章文件路径")
    private String contentPath;

    @ApiModelProperty("发布时间")
    private Date publishDate;

    @ApiModelProperty("更新时间")
    private Date updateDate;

    @ApiModelProperty("文章状态")
    private String status;

    @ApiModelProperty("文章热度")
    private Integer heat;

    @ApiModelProperty("文章标签ID列表")
    private List<Long> tagIds;

    @ApiModelProperty("摘要")
    private String summary;

    @ApiModelProperty("具体内容")
    private String content;

    @ApiModelProperty("作者")
    private String author;
}
