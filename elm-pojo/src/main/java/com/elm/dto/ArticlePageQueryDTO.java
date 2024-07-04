package com.elm.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class ArticlePageQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private Integer page;
    private Integer pageSize;
}
