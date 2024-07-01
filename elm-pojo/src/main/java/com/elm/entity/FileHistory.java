package com.elm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileHistory {
    private int id;
    private String filename;
    private String path;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
