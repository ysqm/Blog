package com.elm.service.impl;

import com.elm.constant.MessageConstant;
import com.elm.context.BaseContext;
import com.elm.dto.UploadFileDTO;
import com.elm.entity.FileHistory;
import com.elm.entity.FileStatus;
import com.elm.exception.*;
import com.elm.mapper.FileMapper;
import com.elm.properties.UploadFileProperties;
import com.elm.result.Result;
import com.elm.service.FileService;
import com.elm.vo.DownloadFileVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mock.web.MockMultipartFile;
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
    public Result uploadFile(MultipartFile file) {
        if(file == null || file.isEmpty()) {
            return Result.error(MessageConstant.FILE_NOT_FOUND);
        }
        String uploadDir = makeFileDir(file);
        FileHistory fileHistory = new FileHistory();
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uuid = UUID.randomUUID().toString();
        File dest = new File(uploadDir, uuid + "." + suffix);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileUploadException(MessageConstant.UPLOAD_FAILED);
        }
        fileHistory.setStatus(FileStatus.HIDE);
        fileHistory.setPath(uploadDir);
        fileHistory.setType(suffix);
        fileHistory.setUuid(uuid);
        fileHistory.setUploadAuthor(BaseContext.getCurrentId());
        fileHistory.setFilename(file.getOriginalFilename());
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

    @Override
    public Result downloadFile(Integer Id) {
        FileHistory fileHistory = new FileHistory();
        DownloadFileVO downloadFileVO = new DownloadFileVO();
        fileHistory = fileMapper.getFileById(Id);
        if(fileHistory == null) {return Result.error(MessageConstant.FILE_NOT_FOUND);}
        BeanUtils.copyProperties(fileHistory, downloadFileVO);
        downloadFileVO.setFile(getMultipartFile(fileHistory.getUuid() + "." + fileHistory.getType(),fileHistory.getPath()));
        return Result.success(downloadFileVO);
    }

    private String makeFileDir(MultipartFile file) {
        String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        Map<String,String> mapExtensions = uploadFileProperties.getExtensions();
        String pathSuffix = mapExtensions.get(fileSuffix);
        if(pathSuffix == null || pathSuffix.isEmpty()) {
            throw new IllegalFileNameException(MessageConstant.ILLEGAL_FILE_NAME);
        }
        File uploadDirFile = new File(uploadFileProperties.getSavePath() + "\\" + pathSuffix);
        if (!uploadDirFile.exists()) {
            boolean mkdirs = uploadDirFile.mkdirs();
            if (!mkdirs) {
                throw new FailedCreateDirectoryException(MessageConstant.FAILED_CREATE_DIRECTORY);
            }
        }
        return uploadDirFile.getAbsolutePath();
    }

    private MultipartFile getMultipartFile(String fileName,String filePath) {

        File file = new File(filePath, fileName);
        if (!file.exists()) {
            // 处理文件不存在的情况
            throw new FileNowFoundException(MessageConstant.FILE_NOT_FOUND);
        }
        // 创建FileSystemResource对象
        FileSystemResource resource = new FileSystemResource(file);
        // 创建MultipartFile对象
        MultipartFile multipartFile;
        try {
            multipartFile = new MockMultipartFile(fileName, resource.getInputStream());
        } catch (Exception e) {
            // 处理文件读取失败的情况
            throw new FileReadException(MessageConstant.FILE_READ_ERROR);
        }

        return multipartFile;
    }
}
