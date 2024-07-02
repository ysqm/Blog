package com.elm.service.impl;

import com.elm.constant.MessageConstant;
import com.elm.context.BaseContext;
import com.elm.entity.FileHistory;
import com.elm.entity.FileStatus;
import com.elm.exception.FailedCreateDirectoryException;
import com.elm.exception.IllegalFileNameException;
import com.elm.mapper.FileMapper;
import com.elm.properties.UploadFileProperties;
import com.elm.result.Result;
import com.elm.service.FileService;
import com.elm.utils.JwtUtil;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileMapper fileMapper;
    @Autowired
    private UploadFileProperties uploadFileProperties;

    @Override
    public Result uploadFile(MultipartFile file) throws FileUploadException {
        if(file == null || file.isEmpty()) {
            return Result.error(MessageConstant.FILE_NOT_FOUND);
        }
        String uploadDir = makeFileDir(file);
        FileHistory fileHistory = new FileHistory();
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uuid = UUID.randomUUID().toString();
        fileHistory.builder()
                .path(uploadDir)
                .type(suffix)
                .uploadAuthor(BaseContext.getCurrentId())
                .filename(file.getOriginalFilename())
                .uuid(uuid)
                .status(FileStatus.HIDE)
                .build();
        File dest = new File(uploadDir, uuid + "." + suffix);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileUploadException(MessageConstant.UPLOAD_FAILED);
        }
        fileMapper.InsertFile(fileHistory);
        return Result.success(fileHistory);
    }

    @Override
    public Result getFileById(Integer Id) {
        FileHistory fileHistory = new FileHistory();
        fileHistory = fileMapper.getFileById(Id);
        if(fileHistory != null) {
            return Result.success(fileHistory);
        } else return Result.error(MessageConstant.FILE_NOT_FOUND);
    }

    @Override
    public Result updateFileById(FileHistory fileHistory) {
        FileHistory file = fileMapper.getFileById(fileHistory.getId());
        if(file != null) {
            Integer msg = fileMapper.UpdateFile(fileHistory);
            return Result.success(msg);
        } else return Result.error(MessageConstant.FILE_NOT_FOUND);
    }

    private String makeFileDir(MultipartFile file) {
        String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        Map<String,String> mapExtensions = uploadFileProperties.getExtensions();
        String pathSuffix = mapExtensions.get(fileSuffix);
        if(pathSuffix == null || pathSuffix.isEmpty()) {
            throw new IllegalFileNameException(MessageConstant.ILLEGAL_FILE_NAME);
        }
        File uploadDirFile = new File(uploadFileProperties.getSavePath() + pathSuffix);
        if (!uploadDirFile.exists()) {
            boolean mkdirs = uploadDirFile.mkdirs();
            if (!mkdirs) {
                throw new FailedCreateDirectoryException(MessageConstant.FAILED_CREATE_DIRECTORY);
            }
        }
        return uploadDirFile.getAbsolutePath();
    }
}
