package com.elm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long articleId;
    private Long userId;
    private String title;
    private String contentPath;
    private Date publishDate;
    private Date updateDate;
    private String status;
    private Integer heat;
    private Boolean isDeleted;
}