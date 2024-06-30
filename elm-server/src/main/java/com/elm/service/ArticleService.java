package com.elm.service;

import com.elm.dto.CreateArticleDTO;
import com.elm.dto.UpdateArticleDTO;
import com.elm.vo.ArticleVO;

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
}
