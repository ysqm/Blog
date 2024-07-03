package com.elm.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "搜索需要返回的内容")
public class ArticleSearchVO {
    private String title;
    private String contentPath;
    private String userId;
    private Integer articleId;
}
