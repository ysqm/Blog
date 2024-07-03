package com.elm.controller.admin;

import com.elm.dto.CreateArticleDTO;
import com.elm.dto.UpdateArticleDTO;
import com.elm.result.Result;
import com.elm.service.ArticleService;
import com.elm.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/create")
    public Result<ArticleVO> createArticle(
            @RequestParam("userId") Long userId,
            @RequestParam("title") String title,
            @RequestParam("status") String status,
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "tagIds", required = false) List<Long> tagIds) {
        CreateArticleDTO dto = new CreateArticleDTO();
        dto.setUserId(userId);
        dto.setTitle(title);
        dto.setStatus(status);
        dto.setFile(file);
        dto.setTagIds(tagIds);
        ArticleVO articleVO = articleService.createArticle(dto);
        return Result.success(articleVO);
    }

    @PutMapping("/update/{articleId}")
    public Result<ArticleVO> updateArticle(
            @PathVariable Long articleId,
            @RequestParam("title") String title,
            @RequestParam("status") String status,
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "tagIds", required = false) List<Long> tagIds) {
        UpdateArticleDTO dto = new UpdateArticleDTO();
        dto.setTitle(title);
        dto.setStatus(status);
        dto.setFile(file);
        dto.setTagIds(tagIds);
        ArticleVO articleVO = articleService.updateArticle(articleId, dto);
        return Result.success(articleVO);
    }

    @DeleteMapping("/delete/{articleId}")
    public Result<Void> deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
        return Result.success();
    }

    @PostMapping("/soft-delete/{articleId}")
    public Result<Void> softDeleteArticle(@PathVariable Long articleId) {
        articleService.softDeleteArticle(articleId);
        return Result.success();
    }

    @GetMapping("/{articleId}")
    public Result<ArticleVO> getArticleById(@PathVariable Long articleId) {
        ArticleVO articleVO = articleService.getArticleById(articleId);
        return Result.success(articleVO);
    }

    @GetMapping("/user/{userId}")
    public Result<List<ArticleVO>> getArticlesByUserId(@PathVariable Long userId) {
        List<ArticleVO> articles = articleService.getArticlesByUserId(userId);
        return Result.success(articles);
    }

    @PostMapping("/{articleId}/hide")
    public Result<ArticleVO> hideArticle(@PathVariable Long articleId) {
        ArticleVO articleVO = articleService.hideArticle(articleId);
        return Result.success(articleVO);
    }

    @PostMapping("/{articleId}/top")
    public Result<ArticleVO> topArticle(@PathVariable Long articleId) {
        ArticleVO articleVO = articleService.topArticle(articleId);
        return Result.success(articleVO);
    }
}
