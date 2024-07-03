package com.elm.controller.admin;

import com.elm.dto.CreateTagDTO;
import com.elm.service.TagService;
import com.elm.vo.TagVO;
import com.elm.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@Slf4j
@Api(tags = "标签相关接口")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/create")
    @ApiOperation("添加标签")
    public Result<TagVO> createTag(@RequestBody CreateTagDTO tagDTO) {
        TagVO tagVO = tagService.createTag(tagDTO);
        return Result.success(tagVO);
    }

    @PostMapping("/approve/{tagId}")
    @ApiOperation("批准标签")
    public Result<TagVO> approveTag(@PathVariable Long tagId) {
        TagVO tagVO = tagService.approveTag(tagId);
        return Result.success(tagVO);
    }

    @GetMapping("/all")
    @ApiOperation("获取所有标签")
    public Result<List<TagVO>> getAllTags() {
        List<TagVO> tags = tagService.getAllTags();
        return Result.success(tags);
    }

    @GetMapping("/pending")
    @ApiOperation("获取待定标签")
    public Result<List<TagVO>> getPendingTags() {
        List<TagVO> tags = tagService.getPendingTags();
        return Result.success(tags);
    }
}
