package com.elm.service;

import com.elm.dto.CreateCommentDTO;
import com.elm.dto.UpdateCommentDTO;
import com.elm.entity.Comment;
import com.elm.vo.CommentVO;
import com.elm.result.Result;

public interface CommentService {
    CommentVO addComment(CreateCommentDTO createCommentDTO);
    Comment getComment(Integer commentId);
    CommentVO updateComment(UpdateCommentDTO updateCommentDTO);
}
