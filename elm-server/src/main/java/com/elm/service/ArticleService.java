package com.elm.service;

import com.elm.dto.ArticlePageQueryDTO;
import com.elm.dto.CreateArticleDTO;
import com.elm.dto.UpdateArticleDTO;
import com.elm.vo.ArticleVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface ArticleService {
    ArticleVO createArticle(CreateArticleDTO articleDTO);
    ArticleVO updateArticle(Long articleId, UpdateArticleDTO articleDTO);
    void deleteArticle(Long articleId);
    void softDeleteArticle(Long articleId);
    ArticleVO getArticleById(Long articleId);
    List<ArticleVO> getArticlesByUserId(Long userId);
    ArticleVO hideArticle(Long articleId);
    ArticleVO topArticle(Long articleId);
    List<ArticleVO> getLatestArticles();
    PageInfo<ArticleVO> getArticlesByPage(ArticlePageQueryDTO queryDTO);
}
