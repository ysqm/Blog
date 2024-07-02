package com.elm.dto;

import com.elm.entity.FileStatus;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "文件信息")
public class UploadFileDTO {
    private Integer id;
    private String filename;
    private FileStatus status;
    private Integer uploadAuthor;
}
