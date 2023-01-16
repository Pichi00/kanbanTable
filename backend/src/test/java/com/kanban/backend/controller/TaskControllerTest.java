package com.kanban.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kanban.backend.config.SecurityConfig;
import com.kanban.backend.enums.Priority;
import com.kanban.backend.mapper.Mapper;
import com.kanban.backend.model.Task;
import com.kanban.backend.model.TaskGroup;
import com.kanban.backend.repository.UserRepository;
import com.kanban.backend.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({TaskController.class, AuthController.class})
@Import({SecurityConfig.class, TokenService.class})
class TaskControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @MockBean
    private TaskService taskService;
    @MockBean
    private TaskGroupService taskGroupService;
    @MockBean
    private TagService tagService;
    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private H2ConsoleProperties h2ConsoleProperties;
    @MockBean
    private Mapper mapper;
    @Autowired
    private MockMvc mvc;

    @Test
    void shouldReturnUnauthorizedGetAll() throws Exception {
        //then
        this.mvc.perform(get("/tasks")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void shouldGetAllTasks() throws Exception {
        //given
        final List<Task> tasks = List.of(new Task());

        //when
        when(this.taskService.getAllTasks()).thenReturn(tasks);

        //then
        this.mvc.perform(get("/tasks"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldGetTaskById() throws Exception {
        //given
        final Long id = 9L;
        final Task task = new Task();

        //when
        when(this.taskService.getTaskById(id)).thenReturn(task);

        //then
        this.mvc.perform(get("/tasks/{id}", id)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldDeleteTaskById() throws Exception {
        //given
        final Long id = 24L;
        final Task task = new Task("1POIUSz", "d8aD48LDjv3R", new TaskGroup(), Collections.emptyList());

        //when
        when(this.taskService.deleteTaskById(id)).thenReturn(task);

        //then
        this.mvc.perform(delete("/tasks/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(task))).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldUpdateTask() throws Exception {
        //given
        final Long id = 6L;
        final Task task = new Task(id, "vd9d8Z8qeqFl", "e7d5FB2", Priority.LOW, "xIJB965Y", "zlaB1G20vG0U", new TaskGroup(), Collections.emptyList());

        //when
        when(this.taskService.updateTask(id, task)).thenReturn(task);

        //then
        this.mvc.perform(put("/tasks/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(task))).andExpect(status().isOk());
    }

}
