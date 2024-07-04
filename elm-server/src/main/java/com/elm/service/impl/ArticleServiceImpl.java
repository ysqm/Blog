package com.elm.service.impl;

import com.elm.dto.ArticlePageQueryDTO;
import com.elm.dto.CreateArticleDTO;
import com.elm.dto.UpdateArticleDTO;
import com.elm.entity.Article;
import com.elm.mapper.ArticleMapper;
import com.elm.properties.UploadFileProperties;
import com.elm.mapper.ArticleTagMapper;
import com.elm.mapper.UserMapper;
import com.elm.service.ArticleService;
import com.elm.vo.ArticleVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UploadFileProperties uploadFileProperties;
    @Autowired
    private ArticleTagMapper articleTagMapper;
    @Autowired
    private UserMapper userMapper;

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
    public PageInfo<ArticleVO> getArticlesByPage(ArticlePageQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPage(), queryDTO.getPageSize());
        List<Article> articles = articleMapper.selectArticlesByPage(queryDTO);
        List<ArticleVO> articleVOs = articles.stream()
                .filter(article -> "published".equals(article.getStatus()))  // 只保留状态为 "published" 的文章
                .map(this::toVO)
                .collect(Collectors.toList());
        return new PageInfo<>(articleVOs);
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
    @Override
    public List<ArticleVO> getLatestArticles() {
        List<Article> articles = articleMapper.getLatestArticles();
        return articles.stream().map(this::toVO).collect(Collectors.toList());
    }


    private ArticleVO toVO(Article article) {
        ArticleVO vo = new ArticleVO();
        vo.setArticleId(article.getArticleId());
        vo.setUserId(article.getUserId());
        String author= userMapper.getUserById(article.getUserId()).getUsername();
        vo.setAuthor(author);
        vo.setTitle(article.getTitle());
        vo.setContentPath(article.getContentPath());
        vo.setPublishDate(article.getPublishDate());
        vo.setUpdateDate(article.getUpdateDate());
        vo.setStatus(article.getStatus());
        vo.setHeat(article.getHeat());
        vo.setTagIds(getArticleTagIds(article.getArticleId()));
        vo.setSummary(generateSummary(article.getContentPath()));
        try {
            vo.setContent(new String(Files.readAllBytes(Paths.get(uploadFileProperties.getSavePath(), article.getContentPath()))));// 生成摘要
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return vo;
    }

    private String generateSummary(String contentPath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(uploadFileProperties.getSavePath(), contentPath)));
            return content.length() > 50 ? content.substring(0, 50) + "..." : content;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to generate summary";
        }
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
