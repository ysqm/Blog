package com.elm.mapper;

import com.elm.entity.LikeDislike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeDislikeMapper {
    void insertOrUpdate(LikeDislike likeDislike);
    void insert(LikeDislike likeDislike);
    void delete(LikeDislike likeDislike);
    void update(LikeDislike existingRecord);
    Integer countByCommentId(@Param("commentId") Integer commentId);
    Integer countByArticleId(@Param("articleId") Integer articleId);
    Integer countByUserId(@Param("userId") Integer userId);

    LikeDislike findByUserIdAndTargetIdAndRecordType(Long userId, Long targetId, String recordType);
}
