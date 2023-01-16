package com.kanban.backend.service;

import com.kanban.backend.exception.ResourceNotFoundException;
import com.kanban.backend.model.Table;
import com.kanban.backend.model.Tag;
import com.kanban.backend.model.Task;
import com.kanban.backend.repository.TagRepository;
import com.kanban.backend.repository.TaskRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @Mock
    private TagRepository tagRepository;
    private TaskRepository taskRepository;

    private TagService subject;

    @BeforeEach
    void setUp() {
        this.subject = new TagService(this.tagRepository, this.taskRepository);
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
        final Tag returnedOutput = new Tag(id, "KW3JqT1T7L9f0", List.of(new Task()), new Table());
        given(this.tagRepository.findById(id)).willReturn(Optional.of(returnedOutput));

        //when
        this.subject.getTagById(id);

        //then
        verify(this.tagRepository).findById(id);
    }

    @Test
    void shouldThrowNotFoundException() {
        //given
        final Long id = 8L;
        final Tag tag = new Tag(id, "iz16I", Collections.emptyList(), new Table());
        String expectedMessage = "Could not find Tag with id " + id.toString();
        given(this.tagRepository.findById(id)).willThrow(new ResourceNotFoundException(Tag.class.getSimpleName(), id));

        //then
        assertThatThrownBy(() -> this.subject.getTagById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(expectedMessage);

        assertThatThrownBy(() -> this.subject.deleteTagById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(expectedMessage);

        assertThatThrownBy(() -> this.subject.updateTag(id, tag))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(expectedMessage);
    }

    @Test
    void shouldAddTag() {
        //given
        final List<Task> tasks = List.of(new Task());
        final Tag givenInput = new Tag("XkRUHwn7", tasks, new Table());

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
        final Tag tagToDelete = new Tag(id, "q556cIWu1", Collections.emptyList(), new Table());
        given(tagRepository.findById(id)).willReturn(Optional.of(tagToDelete));

        //when
        this.subject.deleteTagById(id);

        //then
        verify(this.tagRepository).deleteById(id);
    }
}
