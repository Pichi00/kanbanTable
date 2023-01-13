package com.kanban.backend.service;

import com.kanban.backend.model.Table;
import com.kanban.backend.model.Task;
import com.kanban.backend.model.TaskGroup;
import com.kanban.backend.repository.TaskGroupRepository;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskGroupServiceTest {

    @Mock
    private TaskGroupRepository taskGroupRepository;
    @Mock
    private TaskRepository taskRepository;

    private TaskGroupService subject;

    @BeforeEach
    void setUp() {
        TaskService taskService = new TaskService(this.taskRepository);
        this.subject = new TaskGroupService(this.taskGroupRepository, taskService);
    }

    @Test
    void shouldGetAllTaskGroups() {
        //when
        this.subject.getAllTaskGroups();

        //then
        verify(this.taskGroupRepository).findAll();
    }

    @Test
    void shouldGetTaskGroupById() {
        //given
        final Long id = 20L;
        final TaskGroup returnedOutput = new TaskGroup(id, "zCH65L12b0", new Table(), List.of(new Task()));
        given(this.taskGroupRepository.findById(id)).willReturn(Optional.of(returnedOutput));

        //when
        this.subject.getTaskGroupById(id);

        //then
        verify(this.taskGroupRepository).findById(id);
    }

    @Test
    void shouldAddTaskGroup() {
        //given
        final TaskGroup givenInput = new TaskGroup("XkRUHwn7", new Table(), List.of(new Task()));

        //when
        this.subject.addTaskGroup(givenInput);

        //then
        final ArgumentCaptor<TaskGroup> taskGroupArgumentCaptor = ArgumentCaptor.forClass(TaskGroup.class);
        verify(this.taskGroupRepository).save(taskGroupArgumentCaptor.capture());
        final TaskGroup capturedTaskGroup = taskGroupArgumentCaptor.getValue();

        AssertionsForClassTypes.assertThat(capturedTaskGroup).isEqualTo(givenInput);
    }


    @Test
    void shouldDeleteTaskGroup() {
        //given
        final Long id = 2L;
        final TaskGroup taskGroupToDelete = new TaskGroup(id, "CW7HFs", new Table(), Collections.emptyList());
        given(taskGroupRepository.findById(id)).willReturn(Optional.of(taskGroupToDelete));

        //when
        this.subject.deleteTaskGroupById(id);

        //then
        verify(this.taskGroupRepository).delete(taskGroupToDelete);
    }
}
