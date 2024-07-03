package com.elm.mapper;

import com.elm.entity.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper {
    @Insert("INSERT INTO tags (tag_name, status) VALUES (#{tagName}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "tagId")
    void insertTag(Tag tag);

    void updateTag(Tag tag);
    List<Tag> getAllTags();
    List<Tag> getPendingTags();
    Tag getTagById(@Param("tagId") Long tagId);
}
