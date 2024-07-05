package com.elm.mapper;

import com.elm.entity.LikeDislike;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeDislikeMapper {
    LikeDislike findByUserIdAndTargetIdAndRecordType(Long userId, Long articleId, Long commentId);
    void insert(LikeDislike likeDislike);
    void update(LikeDislike likeDislike);
    void delete(LikeDislike likeDislike);
    Integer countByCommentId(Integer commentId);
    Integer countByArticleId(Integer articleId);
    Integer countByUserId(Integer userId);
}
