package com.elm.vo;

import com.elm.entity.FileStatus;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@ApiOperation("传回文件")
public class DownloadFileVO {
    private Integer id;
    private String filename;
    private String path;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private FileStatus status;
    private String type;
    private String uuid;
    private Integer uploadAuthor;
    private MultipartFile file;
}
