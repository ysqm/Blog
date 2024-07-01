package com.elm.controller.admin;


import com.elm.entity.FileHistory;
import com.elm.properties.UploadFileProperties;
import com.elm.result.Result;
import com.elm.service.FileService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@Slf4j
@Api(tags = "文件相关接口")
public class FileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private UploadFileProperties uploadFileProperties;

    public Result uploadFile(@RequestPart("file") MultipartFile file, @RequestBody FileHistory fileHistory) {
        return fileService.uploadFile(file,fileHistory);
    }

    public Result getFileById(@RequestBody Integer id) {
        return fileService.getFileById(id);
    }

    public Result updateFileById(@RequestBody FileHistory fileHistory) {
        return fileService.updateFileById(fileHistory);
    }
}
