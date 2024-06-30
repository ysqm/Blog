package com.elm.service.impl;

import com.elm.dto.CreateArticleDTO;
import com.elm.dto.UpdateArticleDTO;
import com.elm.entity.Article;
import com.elm.mapper.ArticleMapper;
import com.elm.mapper.ArticleTagMapper;
import com.elm.service.ArticleService;
import com.elm.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    private ArticleTagMapper articleTagMapper;
    @Value("${file.upload.dir}")
    private String uploadDir;// 设置文件上传目录

    @Override
    public ArticleVO createArticle(CreateArticleDTO articleDTO) {
        String contentPath = saveFile(articleDTO.getFile());

        Article article = new Article();
        article.setUserId(articleDTO.getUserId());
        article.setTitle(articleDTO.getTitle());
        article.setContentPath(contentPath);
        article.setStatus(articleDTO.getStatus());
        article.setPublishDate(new Date());
        articleMapper.insertArticle(article);

        saveArticleTags(article.getArticleId(), articleDTO.getTagIds());
        return toVO(article);
    }

    @Override
    public ArticleVO updateArticle(Long articleId, UpdateArticleDTO articleDTO) {
        Article article = articleMapper.getArticleById(articleId);
        if (article == null) {
            throw new RuntimeException("Article not found");
        }

        String contentPath = saveFile(articleDTO.getFile());

        article.setTitle(articleDTO.getTitle());
        article.setContentPath(contentPath);
        article.setStatus(articleDTO.getStatus());
        article.setUpdateDate(new Date());
        articleMapper.updateArticle(article);

        updateArticleTags(article.getArticleId(), articleDTO.getTagIds());
        return toVO(article);
    }

    @Override
    public void deleteArticle(Long articleId) {
        Article article = articleMapper.getArticleById(articleId);
        if (article != null) {
            deleteFile(article.getContentPath());
            articleMapper.deleteArticle(articleId);
            deleteArticleTags(articleId);
        }
    }

    @Override
    public void softDeleteArticle(Long articleId) {
        articleMapper.softDeleteArticle(articleId);
    }

    @Override
    public ArticleVO getArticleById(Long articleId) {
        Article article = articleMapper.getArticleById(articleId);
        return toVO(article);
    }

    @Override
    public List<ArticleVO> getArticlesByUserId(Long userId) {
        List<Article> articles = articleMapper.getArticlesByUserId(userId);
        return articles.stream().map(this::toVO).collect(Collectors.toList());
    }

    @Override
    public ArticleVO hideArticle(Long articleId) {
        articleMapper.hideArticle(articleId);
        return getArticleById(articleId);
    }

    @Override
    public ArticleVO topArticle(Long articleId) {
        articleMapper.topArticle(articleId);
        return getArticleById(articleId);
    }

    private ArticleVO toVO(Article article) {
        ArticleVO vo = new ArticleVO();
        vo.setArticleId(article.getArticleId());
        vo.setUserId(article.getUserId());
        vo.setTitle(article.getTitle());
        vo.setContentPath(article.getContentPath());
        vo.setPublishDate(article.getPublishDate());
        vo.setUpdateDate(article.getUpdateDate());
        vo.setStatus(article.getStatus());
        vo.setHeat(article.getHeat());
        vo.setTagIds(getArticleTagIds(article.getArticleId()));
        return vo;
    }

    private String saveFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        // Ensure the upload directory exists
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            boolean mkdirs = uploadDirFile.mkdirs();
            if (!mkdirs) {
                throw new RuntimeException("Failed to create upload directory");
            }
        }

        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String newFilename = UUID.randomUUID().toString() + fileExtension;

        File dest = new File(uploadDirFile, newFilename);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }

        return newFilename;
    }

    private void deleteFile(String filePath) {
        File file = new File(uploadDir + File.separator + filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    private void saveArticleTags(Long articleId, List<Long> tagIds) {
        if (tagIds != null) {
            tagIds.forEach(tagId -> articleTagMapper.insertArticleTag(articleId, tagId));
        }
    }

    private void updateArticleTags(Long articleId, List<Long> tagIds) {
        articleTagMapper.deleteArticleTagsByArticleId(articleId);
        saveArticleTags(articleId, tagIds);
    }

    private void deleteArticleTags(Long articleId) {
        articleTagMapper.deleteArticleTagsByArticleId(articleId);
    }

    private List<Long> getArticleTagIds(Long articleId) {
        return articleTagMapper.getTagIdsByArticleId(articleId);
    }
}

