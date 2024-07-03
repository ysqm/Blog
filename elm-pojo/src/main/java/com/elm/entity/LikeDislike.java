package com.elm.entity;

import lombok.Data;

@Data
public class LikeDislike {
    private Long recordId;
    private Long userId;
    private Long targetId;
    private String recordType;
    private String recordStatus;
}
