package com.elm.service;

import com.elm.entity.LikeDislike;

public interface LikeDislikeService {
    void addLikeDislike(LikeDislike likeDislike);
    void removeLikeDislike(LikeDislike likeDislike);
    Integer getLikeDislikeCountByCommentId(Integer commentId);
    Integer getLikeDislikeCountByArticleId(Integer articleId);
    Integer getLikeDislikeCountByUserId(Integer userId);
}
