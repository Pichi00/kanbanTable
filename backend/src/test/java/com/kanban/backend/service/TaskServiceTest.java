package com.kanban.backend.service;

import com.kanban.backend.model.*;
import com.kanban.backend.repository.TaskRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    private TaskService subject;

    @BeforeEach
    void setUp(){
        this.subject = new TaskService(this.taskRepository);
    }

    @Test
    void shouldGetAllTasks() {
        //when
        this.subject.getAllTasks();

        //then
        verify(this.taskRepository).findAll();
    }

    @Test
    void shouldGetTaskById() {
        //given
        final Long id = 1L;
        final Task returnedOutput = new Task(id, "Cv27HKG0nJ", new TaskGroup(),List.of(new Tag()));
        given(this.taskRepository.findById(id)).willReturn(Optional.of(returnedOutput));

        //when
        this.subject.getTaskById(id);

        //then
        verify(this.taskRepository).findById(id);
    }

    @Test
    void shouldAddTask() {
        //given
        final Task givenInput = new Task("91XDt",new TaskGroup(),List.of(new Tag()));

        //when
        this.subject.addTask(givenInput);

        //then
        final ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);
        verify(this.taskRepository).save(taskArgumentCaptor.capture());
        final Task capturedTask = taskArgumentCaptor.getValue();

        AssertionsForClassTypes.assertThat(capturedTask).isEqualTo(givenInput);
    }

    @Test
    void shouldDeleteTaskById() {
        //given
        final Long id = 1L;

        //when
        this.subject.deleteTaskById(id);

        //then
        verify(this.taskRepository).deleteById(id);
    }
}