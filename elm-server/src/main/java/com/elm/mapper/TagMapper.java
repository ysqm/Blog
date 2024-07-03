package com.elm.mapper;

import com.elm.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper {
    void insertTag(Tag tag);
    void updateTag(Tag tag);
    List<Tag> getAllTags();
    List<Tag> getPendingTags();
    Tag getTagById(@Param("tagId") Long tagId);
}
