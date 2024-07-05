package com.elm.entity;
import lombok.Data;

@Data
public class LikeDislike {
    private Long recordId;
    private Long userId;
    private Long articleId;
    private Long commentId;
    private String recordStatus;

}
