package com.kanban.backend.service;

import com.kanban.backend.model.*;
import com.kanban.backend.repository.TagRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @Mock
    private TagRepository tagRepository;

    private TagService subject;

    @BeforeEach
    void setUp(){
        this.subject = new TagService(this.tagRepository);
    }

    @Test
    void shouldGetAllTags() {
        //when
        this.subject.getAllTags();

        //then
        verify(this.tagRepository).findAll();
    }

    @Test
    void shouldGetTagById() {
        //given
        final Long id = 1L;
        final Tag returnedOutput = new Tag(id, "KW3JqT1T7L9f0", List.of(new Task()));
        given(this.tagRepository.findById(id)).willReturn(Optional.of(returnedOutput));

        //when
        this.subject.getTagById(id);

        //then
        verify(this.tagRepository).findById(id);
    }

    @Test
    void shouldAddTag() {
        //given
        final List<Task> tasks = List.of(new Task());
        final Tag givenInput = new Tag("XkRUHwn7", tasks);

        //when
        this.subject.addTag(givenInput);

        //then
        final ArgumentCaptor<Tag> tagArgumentCaptor = ArgumentCaptor.forClass(Tag.class);
        verify(this.tagRepository).save(tagArgumentCaptor.capture());
        final Tag capturedTag = tagArgumentCaptor.getValue();

        AssertionsForClassTypes.assertThat(capturedTag).isEqualTo(givenInput);
    }

    @Test
    void shouldDeleteTagById() {
        //given
        final Long id = 1L;

        //when
        this.subject.deleteTagById(id);

        //then
        verify(this.tagRepository).deleteById(id);
    }
}