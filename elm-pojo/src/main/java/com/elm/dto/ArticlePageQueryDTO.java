package com.elm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

@Data
@ApiModel(description = "文章分页查询")
public class ArticlePageQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    @ApiModelProperty("页数")
    private Integer page;
    @ApiModelProperty("页面大小")
    private Integer pageSize;
}
