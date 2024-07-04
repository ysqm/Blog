package com.elm.controller.admin;

import com.elm.dto.ArticlePageQueryDTO;
import com.elm.dto.CreateArticleDTO;
import com.elm.dto.UpdateArticleDTO;
import com.elm.result.Result;
import com.elm.service.ArticleService;
import com.elm.vo.ArticleVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@RestController
@RequestMapping("/articles")
@Slf4j
@Api(tags = "文章相关接口")
@CrossOrigin(origins = "*")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/create")
    @ApiOperation("添加文章")
    public Result<ArticleVO> createArticle(@RequestBody CreateArticleDTO dto) {
        ArticleVO articleVO = articleService.createArticle(dto);
        return Result.success(articleVO);
    }

    @PutMapping("/update/{articleId}")
    @ApiOperation("更新文章")
    public Result<ArticleVO> updateArticle(
            @PathVariable Long articleId,
            @RequestParam("title") String title,
            @RequestParam("status") String status,
            @RequestParam("content") String content,
            @RequestParam(value = "tagIds", required = false) List<Long> tagIds) {
        UpdateArticleDTO dto = new UpdateArticleDTO();
        dto.setTitle(title);
        dto.setStatus(status);
        dto.setContent(content);
        dto.setTagIds(tagIds);
        ArticleVO articleVO = articleService.updateArticle(articleId, dto);
        return Result.success(articleVO);
    }

    @DeleteMapping("/delete/{articleId}")
    @ApiOperation("删除文章")
    public Result<Void> deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
        return Result.success();
    }

    @PostMapping("/soft-delete/{articleId}")
    @ApiOperation("软删除文章")
    public Result<Void> softDeleteArticle(@PathVariable Long articleId) {
        articleService.softDeleteArticle(articleId);
        return Result.success();
    }

    @GetMapping("/{articleId}")
    @ApiOperation("通过文章ID获取文章")
    public Result<ArticleVO> getArticleById(@PathVariable Long articleId) {
        ArticleVO articleVO = articleService.getArticleById(articleId);
        return Result.success(articleVO);
    }

    @GetMapping("/user/{userId}")
    @ApiOperation("通过用户ID获取文章列表")
    public Result<List<ArticleVO>> getArticlesByUserId(@PathVariable Long userId) {
        List<ArticleVO> articles = articleService.getArticlesByUserId(userId);
        return Result.success(articles);
    }

    @PostMapping("/{articleId}/hide")
    @ApiOperation("隐藏文章")
    public Result<ArticleVO> hideArticle(@PathVariable Long articleId) {
        ArticleVO articleVO = articleService.hideArticle(articleId);
        return Result.success(articleVO);
    }

    @PostMapping("/{articleId}/top")
    @ApiOperation("置顶文章")
    public Result<ArticleVO> topArticle(@PathVariable Long articleId) {
        ArticleVO articleVO = articleService.topArticle(articleId);
        return Result.success(articleVO);
    }

    @PostMapping("/{articleId}/latest")
    @ApiOperation("最新文章")
    public Result<List<ArticleVO>> latestArticle(@PathVariable Long articleId) {
        List<ArticleVO> articles = articleService.getLatestArticles();
        return Result.success(articles);
    }

    @PostMapping("/page")
    public PageInfo<ArticleVO> getArticlesByPage(@RequestBody ArticlePageQueryDTO queryDTO) {
        return articleService.getArticlesByPage(queryDTO);
    }

}
