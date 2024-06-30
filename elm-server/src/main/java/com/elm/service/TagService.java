package com.elm.service;

import com.elm.dto.CreateTagDTO;
import com.elm.vo.TagVO;

import java.util.List;

public interface TagService {
    TagVO createTag(CreateTagDTO tagDTO);
    TagVO approveTag(Long tagId);
    List<TagVO> getAllTags();
    List<TagVO> getPendingTags();
}