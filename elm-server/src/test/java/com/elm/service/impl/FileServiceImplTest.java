package com.elm.service.impl;

import com.elm.entity.FileHistory;
import com.elm.mapper.FileMapper;
import com.elm.properties.UploadFileProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class FileServiceImplTest {

    @Mock
    private FileMapper fileMapper;

    @Mock
    private UploadFileProperties uploadFileProperties;

    @InjectMocks
    private FileServiceImpl fileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadFile() throws IOException {
        // 准备测试数据
        MultipartFile file = new MockMultipartFile("test.txt", "test.txt", "text/plain", "test content".getBytes());
        Map<String, String> extensions = new HashMap<>();
        extensions.put("txt", "text");
        when(uploadFileProperties.getExtensions()).thenReturn(extensions);
        when(uploadFileProperties.getSavePath()).thenReturn("test/path");

        // 调用被测试方法
        fileService.uploadFile(file);

        // 验证Mapper方法是否被调用
        verify(fileMapper, times(1)).InsertFile(any(FileHistory.class));
    }

    @Test
    void testGetFileById() {
        // 准备测试数据
        Integer id = 1;
        FileHistory fileHistory = new FileHistory();
        fileHistory.setId(id);
        when(fileMapper.getFileById(id)).thenReturn(fileHistory);

        // 调用被测试方法
        fileService.getFileById(id);

        // 验证Mapper方法是否被调用
        verify(fileMapper, times(1)).getFileById(id);
    }

    @Test
    void testUpdateFileById() {
        // 准备测试数据
        FileHistory fileHistory = new FileHistory();
        fileHistory.setId(1);
        when(fileMapper.getFileById(1)).thenReturn(fileHistory);

        // 调用被测试方法
        fileService.updateFileById(fileHistory);

        // 验证Mapper方法是否被调用
        verify(fileMapper, times(1)).UpdateFile(fileHistory);
    }

    @Test
    void testDownloadFile() {
        // 准备测试数据
        Integer id = 1;
        FileHistory fileHistory = new FileHistory();
        fileHistory.setId(id);
        fileHistory.setUuid("test-uuid");
        fileHistory.setType("txt");
        when(fileMapper.getFileById(id)).thenReturn(fileHistory);

        // 调用被测试方法
        fileService.downloadFile(id);

        // 验证Mapper方法是否被调用
        verify(fileMapper, times(1)).getFileById(id);
    }
}