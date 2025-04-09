package com.elm.service.impl;

import com.elm.dto.CreateTagDTO;
import com.elm.entity.Tag;
import com.elm.mapper.TagMapper;
import com.elm.vo.TagVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class TagServiceImplTest {

    @Mock
    private TagMapper tagMapper;

    @InjectMocks
    private TagServiceImpl tagService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTag() {
        CreateTagDTO tagDTO = new CreateTagDTO();
        tagDTO.setTagName("Test Tag");

        Tag tag = new Tag();
        tag.setTagId(1L);
        tag.setTagName("Test Tag");
        tag.setStatus("pending");

        // 由于 insertTag 方法返回值是 void，不需要 thenReturn
        doNothing().when(tagMapper).insertTag(any(Tag.class));

        TagVO result = tagService.createTag(tagDTO);

        assertNotNull(result);
        assertEquals("Test Tag", result.getTagName());
        verify(tagMapper, times(1)).insertTag(any(Tag.class));
    }

    @Test
    void testApproveTag() {
        Long tagId = 1L;
        Tag tag = new Tag();
        tag.setTagId(tagId);
        tag.setTagName("Test Tag");
        tag.setStatus("pending");

        when(tagMapper.getTagById(tagId)).thenReturn(tag);
        // 由于 updateTag 方法返回值是 void，不需要 thenReturn
        doNothing().when(tagMapper).updateTag(any(Tag.class));

        TagVO result = tagService.approveTag(tagId);

        assertNotNull(result);
        assertEquals("approved", result.getStatus());
        verify(tagMapper, times(1)).getTagById(tagId);
        verify(tagMapper, times(1)).updateTag(any(Tag.class));
    }

    @Test
    void testGetAllTags() {
        Tag tag1 = new Tag();
        tag1.setTagId(1L);
        tag1.setTagName("Tag 1");
        Tag tag2 = new Tag();
        tag2.setTagId(2L);
        tag2.setTagName("Tag 2");
        List<Tag> tags = Arrays.asList(tag1, tag2);

        when(tagMapper.getAllTags()).thenReturn(tags);

        List<TagVO> result = tagService.getAllTags();

        assertNotNull(result);
        assertEquals(tags.size(), result.size());
        verify(tagMapper, times(1)).getAllTags();
    }

    @Test
    void testGetPendingTags() {
        Tag tag1 = new Tag();
        tag1.setTagId(1L);
        tag1.setTagName("Tag 1");
        tag1.setStatus("pending");
        Tag tag2 = new Tag();
        tag2.setTagId(2L);
        tag2.setTagName("Tag 2");
        tag2.setStatus("pending");
        List<Tag> tags = Arrays.asList(tag1, tag2);

        when(tagMapper.getPendingTags()).thenReturn(tags);

        List<TagVO> result = tagService.getPendingTags();

        assertNotNull(result);
        assertEquals(tags.size(), result.size());
        verify(tagMapper, times(1)).getPendingTags();
    }
}