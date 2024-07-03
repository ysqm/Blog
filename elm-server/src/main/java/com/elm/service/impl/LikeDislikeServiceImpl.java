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
        // 检查是否已经存在记录
        LikeDislike existingRecord = likeDislikeMapper.findByUserIdAndTargetIdAndRecordType(
                likeDislike.getUserId(), likeDislike.getTargetId(), likeDislike.getRecordType());
        if (existingRecord != null) {
            // 如果存在，则更新记录
            existingRecord.setRecordStatus(likeDislike.getRecordStatus());
            likeDislikeMapper.update(existingRecord);
        } else {
            // 否则插入新记录
            likeDislikeMapper.insert(likeDislike);
        }
    }

    @Override
    public void removeLikeDislike(LikeDislike likeDislike) {
        // 直接删除记录
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
