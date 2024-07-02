package com.elm.controller.admin;

import com.elm.dto.CreateTagDTO;
import com.elm.service.TagService;
import com.elm.vo.TagVO;
import com.elm.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@CrossOrigin(origins = "http://localhost:5174") // 允许从特定的前端地址访问
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/create")
    public Result<TagVO> createTag(@RequestBody CreateTagDTO tagDTO) {
        TagVO tagVO = tagService.createTag(tagDTO);
        return Result.success(tagVO);
    }

    @PostMapping("/approve/{tagId}")
    public Result<TagVO> approveTag(@PathVariable Long tagId) {
        TagVO tagVO = tagService.approveTag(tagId);
        return Result.success(tagVO);
    }

    @GetMapping("/all")
    public Result<List<TagVO>> getAllTags() {
        List<TagVO> tags = tagService.getAllTags();
        return Result.success(tags);
    }

    @GetMapping("/pending")
    public Result<List<TagVO>> getPendingTags() {
        List<TagVO> tags = tagService.getPendingTags();
        return Result.success(tags);
    }
}
