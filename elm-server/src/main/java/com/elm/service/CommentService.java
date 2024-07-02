package com.elm.service;

import com.elm.dto.CreateCommentDTO;
import com.elm.dto.UpdateCommentDTO;
import com.elm.entity.Comment;
import com.elm.result.Result;

public interface CommentService {
    Result addComment(CreateCommentDTO createCommentDTO);
    Comment getComment(Integer commentId);
    Result updateComment(UpdateCommentDTO updateCommentDTO);
}
