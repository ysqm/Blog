package com.elm.service.impl;

import com.elm.entity.LikeDislike;
import com.elm.mapper.LikeDislikeMapper;
import com.elm.service.LikeDislikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeDislikeServiceImpl implements LikeDislikeService {

    @Autowired
    private LikeDislikeMapper likeDislikeMapper;

    @Override
    public void addLikeDislike(LikeDislike likeDislike) {
        likeDislikeMapper.insertOrUpdate(likeDislike);
    }

    @Override
    public void removeLikeDislike(LikeDislike likeDislike) {
        likeDislikeMapper.delete(likeDislike);
    }

    @Override
    public Integer getLikeDislikeCountByCommentId(Integer commentId) {
        return likeDislikeMapper.countByCommentId(commentId);
    }

    @Override
    public Integer getLikeDislikeCountByArticleId(Integer articleId) {
        return likeDislikeMapper.countByArticleId(articleId);
    }

    @Override
    public Integer getLikeDislikeCountByUserId(Integer userId) {
        return likeDislikeMapper.countByUserId(userId);
    }
}
