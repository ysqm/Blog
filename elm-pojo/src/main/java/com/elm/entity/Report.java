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
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer reporterId;
    private Integer reportedContentId;
    private String contentType;
    private String reason;
    private LocalDateTime reportDate;
    private String status;
}