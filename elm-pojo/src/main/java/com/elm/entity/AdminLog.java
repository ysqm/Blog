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
public class AdminLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer adminId;
    private String action;
    private LocalDateTime actionDate;
}