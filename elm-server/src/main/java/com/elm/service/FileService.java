package com.elm.service;

import com.elm.entity.FileHistory;
import com.elm.result.Result;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    Result uploadFile(MultipartFile file);
    Result getFileById(Integer Id);
    Result updateFileById(FileHistory fileHistory);
}
