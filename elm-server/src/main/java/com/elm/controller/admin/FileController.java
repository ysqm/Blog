package com.elm.controller.admin;


import com.elm.entity.FileHistory;
import com.elm.entity.FileStatus;
import com.elm.properties.UploadFileProperties;
import com.elm.result.Result;
import com.elm.service.FileService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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


    @PostMapping("/upload")
    public Result uploadFile(@RequestPart("file") MultipartFile file) {return fileService.uploadFile(file);}

    @PostMapping("/download")
    public Result downloadFile(Integer id) {return fileService.downloadFile(id);}

    @GetMapping("/getById")
    public Result getFileById(Integer id) {
        return fileService.getFileById(id);
    }

    @PostMapping("/hide")
    public Result hideFile(Integer id) {
        FileHistory fileHistory = new FileHistory();
        fileHistory.setId(id);
        fileHistory.setStatus(FileStatus.HIDE);
        return fileService.updateFileById(fileHistory);
    }

    @PostMapping("/delete")
    public Result deleteFile(Integer id) {
        FileHistory fileHistory = new FileHistory();
        fileHistory.setId(id);
        fileHistory.setStatus(FileStatus.DELETE);
        return fileService.updateFileById(fileHistory);
    }

    @PostMapping("/recover")
    public Result recoverFile(Integer id) {
        FileHistory fileHistory = new FileHistory();
        fileHistory.setId(id);
        fileHistory.setStatus(FileStatus.NORMAL);
        return fileService.updateFileById(fileHistory);
    }

    @PostMapping("/rename")
    public Result updateFile(Integer id,String filename) {
        FileHistory fileHistory = new FileHistory();
        fileHistory.setId(id);
        fileHistory.setFilename(filename);
        return fileService.updateFileById(fileHistory);
    }
}
