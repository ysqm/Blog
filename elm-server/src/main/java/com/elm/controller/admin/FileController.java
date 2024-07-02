package com.elm.controller.admin;


import com.elm.entity.FileHistory;
import com.elm.entity.FileStatus;
import com.elm.properties.UploadFileProperties;
import com.elm.result.Result;
import com.elm.service.FileService;
import com.elm.vo.DownloadFileVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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
    @ApiOperation("上传文件")
    public Result uploadFile(@RequestPart("file") MultipartFile file) {return fileService.uploadFile(file);}

    @GetMapping("/download/{id}")
    @ApiOperation("获取文件内容")
    public ResponseEntity downloadFile(@PathVariable Integer id) throws IOException {
        Result<DownloadFileVO> result = fileService.downloadFile(id);
        DownloadFileVO downloadFileVO = result.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + downloadFileVO.getFilename());
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        InputStream inputStream = downloadFileVO.getFile().getInputStream();

        // 将输入流的内容读取为字节数组
        byte[] fileBytes = IOUtils.toByteArray(inputStream);
        // 创建返回的ResponseEntity
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(fileBytes);
    }

    @GetMapping("/getById")
    @ApiOperation("获取文件信息")
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
