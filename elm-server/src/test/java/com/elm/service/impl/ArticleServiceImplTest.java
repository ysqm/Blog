package com.elm.service.impl;

import com.elm.dto.ArticlePageQueryDTO;
import com.elm.dto.CreateArticleDTO;
import com.elm.dto.UpdateArticleDTO;
import com.elm.entity.Article;
import com.elm.entity.User;
import com.elm.mapper.ArticleMapper;
import com.elm.mapper.ArticleTagMapper;
import com.elm.mapper.UserMapper;
import com.elm.properties.UploadFileProperties;
import com.elm.service.ArticleService;
import com.elm.vo.ArticleVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ArticleServiceImplTest {

    @Mock
    private ArticleMapper articleMapper;

    @Mock
    private UploadFileProperties uploadFileProperties;

    @Mock
    private ArticleTagMapper articleTagMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateArticle_Success() {
        CreateArticleDTO articleDTO = new CreateArticleDTO();
        articleDTO.setUserId(1);
        articleDTO.setTitle("Test Article");
        articleDTO.setContent("Test content");
        articleDTO.setStatus("published");
        articleDTO.setTagIds(Arrays.asList(1L, 2L));

        Article article = new Article();
        article.setArticleId(1L);
        article.setUserId(1);
        article.setTitle("Test Article");
        article.setContentPath("testPath");
        article.setStatus("published");
        article.setPublishDate(new Date());

        when(articleMapper.insertArticle(any(Article.class))).thenReturn(1);
        when(articleMapper.getArticleById(1L)).thenReturn(article);
        when(userMapper.getUserById(1)).thenReturn(new User());
        when(articleTagMapper.getTagIdsByArticleId(1L)).thenReturn(Arrays.asList(1L, 2L));

        ArticleVO result = articleService.createArticle(articleDTO);

        assertNotNull(result);
        assertEquals("Test Article", result.getTitle());
    }

    @Test
    void testGetArticlesByPage_Success() {
        ArticlePageQueryDTO queryDTO = new ArticlePageQueryDTO();
        queryDTO.setPage(1);
        queryDTO.setPageSize(10);

        Article article = new Article();
        article.setStatus("published");
        Page<Article> page = new Page<>();
        page.add(article);
        page.setTotal(1);

        when(articleMapper.selectArticlesByPage(queryDTO)).thenReturn(page);
        when(userMapper.getUserById((int) anyLong())).thenReturn(new User());
        when(articleTagMapper.getTagIdsByArticleId(anyLong())).thenReturn(Arrays.asList(1L));

        PageInfo<ArticleVO> result = articleService.getArticlesByPage(queryDTO);

        assertNotNull(result);
        assertEquals(1, result.getList().size());
    }

    @Test
    void testUpdateArticle_Success() {
        Long articleId = 1L;
        UpdateArticleDTO articleDTO = new UpdateArticleDTO();
        articleDTO.setTitle("Updated Article");
        articleDTO.setContent("Updated content");
        articleDTO.setStatus("published");
        articleDTO.setTagIds(Arrays.asList(1L, 2L));

        Article article = new Article();
        article.setArticleId(1L);
        article.setTitle("Old Article");
        article.setContentPath("oldPath");
        article.setStatus("draft");

        when(articleMapper.getArticleById(articleId)).thenReturn(article);
        when(articleMapper.updateArticle(any(Article.class))).thenReturn(1);
        when(userMapper.getUserById(1)).thenReturn(new User());
        when(articleTagMapper.getTagIdsByArticleId(1L)).thenReturn(Arrays.asList(1L, 2L));

        ArticleVO result = articleService.updateArticle(articleId, articleDTO);

        assertNotNull(result);
        assertEquals("Updated Article", result.getTitle());
    }

    @Test
    void testDeleteArticle_Success() {
        Long articleId = 1L;
        Article article = new Article();
        article.setArticleId(1L);
        article.setContentPath("testPath");

        when(articleMapper.getArticleById(articleId)).thenReturn(article);

        articleService.deleteArticle(articleId);

        verify(articleMapper, times(1)).deleteArticle(articleId);
        verify(articleTagMapper, times(1)).deleteArticleTagsByArticleId(articleId);
    }

    @Test
    void testSoftDeleteArticle_Success() {
        Long articleId = 1L;

        articleService.softDeleteArticle(articleId);

        verify(articleMapper, times(1)).softDeleteArticle(articleId);
    }

    @Test
    void testGetArticleById_Success() {
        Long articleId = 1L;
        Article article = new Article();
        article.setArticleId(1L);
        article.setTitle("Test Article");

        when(articleMapper.getArticleById(articleId)).thenReturn(article);
        when(userMapper.getUserById((int) anyLong())).thenReturn(new User());
        when(articleTagMapper.getTagIdsByArticleId(anyLong())).thenReturn(Arrays.asList(1L));

        ArticleVO result = articleService.getArticleById(articleId);

        assertNotNull(result);
        assertEquals("Test Article", result.getTitle());
    }

    @Test
    void testGetArticlesByUserId_Success() {
        Long userId = 1L;
        Article article = new Article();
        article.setUserId(1);
        article.setTitle("Test Article");
        List<Article> articles = Arrays.asList(article);

        when(articleMapper.getArticlesByUserId(userId)).thenReturn(articles);
        when(userMapper.getUserById((int) anyLong())).thenReturn(new User());
        when(articleTagMapper.getTagIdsByArticleId(anyLong())).thenReturn(Arrays.asList(1L));

        List<ArticleVO> result = articleService.getArticlesByUserId(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testHideArticle_Success() {
        Long articleId = 1L;
        Article article = new Article();
        article.setArticleId(1L);
        article.setTitle("1");

        when(articleMapper.getArticleById(articleId)).thenReturn(article);
        when(userMapper.getUserById((int) anyLong())).thenReturn(new User());
        when(articleTagMapper.getTagIdsByArticleId(anyLong())).thenReturn(Arrays.asList(1L));

        ArticleVO result = articleService.hideArticle(articleId);

        assertNotNull(result);
        assertEquals("Test Article", result.getTitle());
    }

    @Test
    void testTopArticle_Success() {
        Long articleId = 1L;
        Article article = new Article();
        article.setArticleId(1L);
        article.setTitle("Test Article");

        when(articleMapper.getArticleById(articleId)).thenReturn(article);
        when(userMapper.getUserById((int) anyLong())).thenReturn(new User());
        when(articleTagMapper.getTagIdsByArticleId(anyLong())).thenReturn(Arrays.asList(1L));

        ArticleVO result = articleService.topArticle(articleId);

        assertNotNull(result);
        assertEquals("Test Article", result.getTitle());
    }

    @Test
    void testGetLatestArticles_Success() {
        Article article = new Article();
        article.setTitle("Test Article");
        List<Article> articles = Arrays.asList(article);

        when(articleMapper.getLatestArticles()).thenReturn(articles);
        when(userMapper.getUserById((int) anyLong())).thenReturn(new User());
        when(articleTagMapper.getTagIdsByArticleId(anyLong())).thenReturn(Arrays.asList(1L));

        List<ArticleVO> result = articleService.getLatestArticles();

        assertNotNull(result);
        assertEquals(1, result.size());
    }
}