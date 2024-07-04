package com.elm.service.impl;

import com.elm.dto.CreateArticleDTO;
import com.elm.dto.UpdateArticleDTO;
import com.elm.entity.Article;
import com.elm.mapper.ArticleMapper;
import com.elm.properties.UploadFileProperties;
import com.elm.mapper.ArticleTagMapper;
import com.elm.service.ArticleService;
import com.elm.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UploadFileProperties uploadFileProperties;
    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public ArticleVO createArticle(CreateArticleDTO articleDTO) {
        String contentPath = saveFile(articleDTO.getContent(), articleDTO.getTitle());

        Article article = new Article();
        article.setUserId(articleDTO.getUserId());
        article.setTitle(articleDTO.getTitle());
        article.setContentPath(contentPath);
        article.setStatus(articleDTO.getStatus());
        article.setPublishDate(new Date());
        articleMapper.insertArticle(article);
        Long articleId = article.getArticleId();

        // 保存标签关联
        if (articleDTO.getTagIds() != null) {
            saveArticleTags(articleId, articleDTO.getTagIds());
        }
        return toVO(article);
    }

    @Override
    public ArticleVO updateArticle(Long articleId, UpdateArticleDTO articleDTO) {
        Article article = articleMapper.getArticleById(articleId);
        if (article == null) {
            throw new RuntimeException("Article not found");
        }

        String contentPath = saveFile(articleDTO.getContent(), articleDTO.getTitle());

        article.setTitle(articleDTO.getTitle());
        article.setContentPath(contentPath);
        article.setStatus(articleDTO.getStatus());
        article.setUpdateDate(new Date());
        articleMapper.updateArticle(article);

        // 更新标签关联
        if (articleDTO.getTagIds() != null) {
            // 先删除旧的标签关联
            articleTagMapper.deleteArticleTagsByArticleId(articleId);
            // 保存新的标签关联
            saveArticleTags(articleId, articleDTO.getTagIds());
        }
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

    private String saveFile(String base64Content, String title) {
        if (base64Content == null || base64Content.isEmpty()) {
            throw new RuntimeException("File content is empty");
        }

        // Ensure the upload directory exists
        File uploadDirFile = new File(uploadFileProperties.getSavePath());
        if (!uploadDirFile.exists()) {
            boolean mkdirs = uploadDirFile.mkdirs();
            if (!mkdirs) {
                throw new RuntimeException("Failed to create upload directory");
            }
        }

        // Decode Base64 content
        byte[] decodedBytes = Base64.getDecoder().decode(base64Content);

        String fileExtension = ".md"; // Assuming the content is markdown
        String newFilename = UUID.randomUUID().toString() + fileExtension;

        File dest = new File(uploadDirFile, newFilename);
        try (FileOutputStream fos = new FileOutputStream(dest)) {
            fos.write(decodedBytes);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }

        return newFilename;
    }

    private void deleteFile(String filePath) {
        File file = new File(uploadFileProperties.getSavePath() + File.separator + filePath);
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
