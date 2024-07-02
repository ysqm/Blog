package com.elm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long commentId;
    private Long articleId;
    private Long userId;
    private String content;
    private LocalDateTime commentDate;
    private Long parentCommentId;
}