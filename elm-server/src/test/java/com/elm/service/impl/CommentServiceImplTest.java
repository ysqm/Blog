package com.elm.service.impl;

import com.elm.dto.CreateCommentDTO;
import com.elm.dto.UpdateCommentDTO;
import com.elm.entity.Comment;
import com.elm.mapper.ArticleMapper;
import com.elm.mapper.CommentMapper;
import com.elm.mapper.UserMapper;
import com.elm.result.Result;
import com.elm.vo.CommentVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CommentServiceImplTest {

    @Mock
    private CommentMapper commentMapper;

    @Mock
    private ArticleMapper articleMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddComment() {
        CreateCommentDTO createCommentDTO = new CreateCommentDTO();
        createCommentDTO.setUserId(1L);
        createCommentDTO.setArticleId(1L);
        createCommentDTO.setContent("Test comment");

        Comment comment = new Comment();
        comment.setCommentId(1L);
        comment.setUserId(1L);
        comment.setArticleId(1L);
        comment.setContent("Test comment");
        comment.setCommentDate(LocalDateTime.now());

        when(commentMapper.createComment(any(Comment.class))).thenReturn(1);

        Result<CommentVO> result = commentService.addComment(createCommentDTO);

        assertNotNull(result);
        assertEquals(1, result.getCode());
        verify(commentMapper, times(1)).createComment(any(Comment.class));
    }

    @Test
    void testGetComment() {
        Long commentId = 1L;
        Comment comment = new Comment();
        comment.setCommentId(commentId);

        when(commentMapper.getCommentById(commentId)).thenReturn(comment);

        Comment result = commentService.getComment(commentId);

        assertNotNull(result);
        assertEquals(commentId, result.getCommentId());
        verify(commentMapper, times(1)).getCommentById(commentId);
    }

    @Test
    void testUpdateComment() {
        UpdateCommentDTO updateCommentDTO = new UpdateCommentDTO();
        updateCommentDTO.setCommentId(1L);
        updateCommentDTO.setContent("Updated comment");

        Comment comment = new Comment();
        comment.setCommentId(1L);
        comment.setContent("Updated comment");
        comment.setCommentDate(LocalDateTime.now());

        when(commentMapper.updateComment(any(Comment.class))).thenReturn(1);

        Result<CommentVO> result = commentService.updateComment(updateCommentDTO);

        assertNotNull(result);
        assertEquals(1, result.getCode());
        verify(commentMapper, times(1)).updateComment(any(Comment.class));
    }

    @Test
    void testDeleteComment() {
        Long commentId = 1L;

        when(commentMapper.deleteComment(commentId)).thenReturn(1);

        Result<Void> result = commentService.deleteComment(commentId);

        assertNotNull(result);
        assertEquals(1, result.getCode());
        verify(commentMapper, times(1)).deleteComment(commentId);
    }

    @Test
    void testGetCommentsByUserId() {
        Long userId = 1L;
        Comment comment1 = new Comment();
        comment1.setUserId(userId);
        Comment comment2 = new Comment();
        comment2.setUserId(userId);
        List<Comment> comments = Arrays.asList(comment1, comment2);

        when(commentMapper.getCommentsByUserId(userId)).thenReturn(comments);

        List<CommentVO> result = commentService.getCommentsByUserId(userId);

        assertNotNull(result);
        assertEquals(comments.size(), result.size());
        verify(commentMapper, times(1)).getCommentsByUserId(userId);
    }

    @Test
    void testGetCommentsByArticleId() {
        Long articleId = 1L;
        Comment comment1 = new Comment();
        comment1.setArticleId(articleId);
        Comment comment2 = new Comment();
        comment2.setArticleId(articleId);
        List<Comment> comments = Arrays.asList(comment1, comment2);

        when(commentMapper.getCommentsByArticleId(articleId)).thenReturn(comments);

        List<CommentVO> result = commentService.getCommentsByArticleId(articleId);

        assertNotNull(result);
        assertEquals(comments.size(), result.size());
        verify(commentMapper, times(1)).getCommentsByArticleId(articleId);
    }

    @Test
    void testGetCommentsByParentCommentId() {
        Long parentCommentId = 1L;
        Comment comment1 = new Comment();
        comment1.setParentCommentId(parentCommentId);
        Comment comment2 = new Comment();
        comment2.setParentCommentId(parentCommentId);
        List<Comment> comments = Arrays.asList(comment1, comment2);

        when(commentMapper.getCommentsByParentCommentId(parentCommentId)).thenReturn(comments);

        List<CommentVO> result = commentService.getCommentsByParentCommentId(parentCommentId);

        assertNotNull(result);
        assertEquals(comments.size(), result.size());
        verify(commentMapper, times(1)).getCommentsByParentCommentId(parentCommentId);
    }

    @Test
    void testCreateCommentArticleNotExist() {
        CreateCommentDTO createCommentDTO = new CreateCommentDTO();
        createCommentDTO.setUserId(1L);
        createCommentDTO.setArticleId(0L);
        createCommentDTO.setParentCommentId(2L);
        createCommentDTO.setContent("12345");

        when(articleMapper.getArticleById(0L)).thenReturn(null); // 模拟文章不存在

        Result<CommentVO> result = commentService.addComment(createCommentDTO);

        assertNotNull(result);
        assertEquals(0, result.getCode()); // 假设文章不存在时返回的 code 为 0
        verify(commentMapper, never()).createComment(any(Comment.class));
    }

    @Test
    void testCreateCommentUserNotExist() {
        CreateCommentDTO createCommentDTO = new CreateCommentDTO();
        createCommentDTO.setUserId(0L);
        createCommentDTO.setArticleId(1L);
        createCommentDTO.setParentCommentId(2L);
        createCommentDTO.setContent("12345");

        when(userMapper.getUserById(0)).thenReturn(null); // 模拟用户不存在

        Result<CommentVO> result = commentService.addComment(createCommentDTO);

        assertNotNull(result);
        assertEquals(0, result.getCode()); // 假设用户不存在时返回的 code 为 0
        verify(commentMapper, never()).createComment(any(Comment.class));
    }

    @Test
    void testGetCommentsByArticleNotExist() {
        Long articleId = 0L;

        when(articleMapper.getArticleById(articleId)).thenReturn(null); // 模拟文章不存在
        when(commentMapper.getCommentsByArticleId(articleId)).thenReturn(null);

        List<CommentVO> result = commentService.getCommentsByArticleId(articleId);

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(commentMapper, times(1)).getCommentsByArticleId(articleId);
    }
}