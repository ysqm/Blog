package com.elm.service.impl;

import com.elm.entity.LikeDislike;
import com.elm.mapper.LikeDislikeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class LikeDislikeServiceImplTest {

    @Mock
    private LikeDislikeMapper likeDislikeMapper;

    @InjectMocks
    private LikeDislikeServiceImpl likeDislikeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddLikeDislike_ExistingRecord() {
        // 准备测试数据
        LikeDislike likeDislike = new LikeDislike();
        likeDislike.setUserId(1L);
        likeDislike.setArticleId(1L);
        likeDislike.setCommentId(1L);
        likeDislike.setRecordStatus("1");

        LikeDislike existingRecord = new LikeDislike();
        existingRecord.setUserId(1L);
        existingRecord.setArticleId(1L);
        existingRecord.setCommentId(1L);
        existingRecord.setRecordStatus("0");

        // 模拟Mapper方法的返回值
        when(likeDislikeMapper.findByUserIdAndTargetIdAndRecordType(1L, 1L, 1L)).thenReturn(existingRecord);

        // 调用被测试方法
        likeDislikeService.addLikeDislike(likeDislike);

        // 验证Mapper方法是否被调用
        verify(likeDislikeMapper, times(1)).update(existingRecord);
        verify(likeDislikeMapper, never()).insert(likeDislike);
    }

    @Test
    void testAddLikeDislike_NewRecord() {
        // 准备测试数据
        LikeDislike likeDislike = new LikeDislike();
        likeDislike.setUserId(1L);
        likeDislike.setArticleId(1L);
        likeDislike.setCommentId(1L);
        likeDislike.setRecordStatus("1");

        // 模拟Mapper方法的返回值
        when(likeDislikeMapper.findByUserIdAndTargetIdAndRecordType(1L, 1L, 1L)).thenReturn(null);

        // 调用被测试方法
        likeDislikeService.addLikeDislike(likeDislike);

        // 验证Mapper方法是否被调用
        verify(likeDislikeMapper, never()).update(any(LikeDislike.class));
        verify(likeDislikeMapper, times(1)).insert(likeDislike);
    }

    @Test
    void testRemoveLikeDislike() {
        // 准备测试数据
        LikeDislike likeDislike = new LikeDislike();
        likeDislike.setUserId(1L);
        likeDislike.setArticleId(1L);
        likeDislike.setCommentId(1L);
        likeDislike.setRecordStatus("1");

        // 调用被测试方法
        likeDislikeService.removeLikeDislike(likeDislike);

        // 验证Mapper方法是否被调用
        verify(likeDislikeMapper, times(1)).delete(likeDislike);
    }

    @Test
    void testGetLikeDislikeCountByCommentId() {
        // 准备测试数据
        Integer commentId = 1;
        Integer expectedCount = 10;

        // 模拟Mapper方法的返回值
        when(likeDislikeMapper.countByCommentId(commentId)).thenReturn(expectedCount);

        // 调用被测试方法
        Integer actualCount = likeDislikeService.getLikeDislikeCountByCommentId(commentId);

        // 验证结果
        org.junit.jupiter.api.Assertions.assertEquals(expectedCount, actualCount);
    }

    @Test
    void testGetLikeDislikeCountByArticleId() {
        // 准备测试数据
        Integer articleId = 1;
        Integer expectedCount = 20;

        // 模拟Mapper方法的返回值
        when(likeDislikeMapper.countByArticleId(articleId)).thenReturn(expectedCount);

        // 调用被测试方法
        Integer actualCount = likeDislikeService.getLikeDislikeCountByArticleId(articleId);

        // 验证结果
        org.junit.jupiter.api.Assertions.assertEquals(expectedCount, actualCount);
    }

    @Test
    void testGetLikeDislikeCountByUserId() {
        // 准备测试数据
        Integer userId = 1;
        Integer expectedCount = 30;

        // 模拟Mapper方法的返回值
        when(likeDislikeMapper.countByUserId(userId)).thenReturn(expectedCount);

        // 调用被测试方法
        Integer actualCount = likeDislikeService.getLikeDislikeCountByUserId(userId);

        // 验证结果
        org.junit.jupiter.api.Assertions.assertEquals(expectedCount, actualCount);
    }
}