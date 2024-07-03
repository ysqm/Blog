package com.elm.service.impl;

import com.elm.dto.CreateTagDTO;
import com.elm.entity.Tag;
import com.elm.mapper.TagMapper;
import com.elm.service.TagService;
import com.elm.vo.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public TagVO createTag(CreateTagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setTagName(tagDTO.getTagName());
        tag.setStatus("pending");
        tagMapper.insertTag(tag);
        return toVO(tag);
    }

    @Override
    public TagVO approveTag(Long tagId) {
        Tag tag = tagMapper.getTagById(tagId);
        if (tag != null && "pending".equals(tag.getStatus())) {
            tag.setStatus("approved");
            tagMapper.updateTag(tag);
        }
        return toVO(tag);
    }

    @Override
    public List<TagVO> getAllTags() {
        List<Tag> tags = tagMapper.getAllTags();
        return tags.stream().map(this::toVO).collect(Collectors.toList());
    }

    @Override
    public List<TagVO> getPendingTags() {
        List<Tag> tags = tagMapper.getPendingTags();
        return tags.stream().map(this::toVO).collect(Collectors.toList());
    }

    private TagVO toVO(Tag tag) {
        TagVO vo = new TagVO();
        vo.setTagId(tag.getTagId());
        vo.setTagName(tag.getTagName());
        vo.setStatus(tag.getStatus());
        return vo;
    }
}
