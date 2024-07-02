package com.elm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "创建评论时传递的数据模型")
public class CreateCommentDTO implements Serializable {

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("评论ID")
    private Long commentId;

    @ApiModelProperty("文章ID")
    private Long articleId;

    @ApiModelProperty("发布时间")
    private LocalDateTime commentDate;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("父评论ID")
    private Long parentCommentId;

    // Getters and Setters

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }
}
