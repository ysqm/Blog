package com.elm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleTagMapper {
    void insertArticleTag(@Param("articleId") Long articleId, @Param("tagId") Long tagId);
    void deleteArticleTagsByArticleId(@Param("articleId") Long articleId);
    List<Long> getTagIdsByArticleId(@Param("articleId") Long articleId);
}
