package com.elm.mapper;

import com.elm.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    Article getArticleById(Long articleId);
    List<Article> getArticlesByUserId(Long userId);
    Integer insertArticle(Article article);
    Integer updateArticle(Article article);
    Integer deleteArticle(Long articleId);
    Integer softDeleteArticle(Long articleId);
    Integer hideArticle(Long articleId);
    Integer topArticle(Long articleId);
    List<Article> getLatestArticles();
}
