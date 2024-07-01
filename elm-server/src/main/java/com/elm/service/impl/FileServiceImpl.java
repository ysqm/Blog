package com.elm.service.impl;

import com.elm.entity.FileHistory;
import com.elm.result.Result;
import com.elm.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public Result uploadFile(MultipartFile file, FileHistory fileHistory) {
        return null;
    }

    @Override
    public Result getFileById(int Id) {
        return null;
    }

    @Override
    public Result updateFileById(FileHistory fileHistory) {
        return null;
    }
}
