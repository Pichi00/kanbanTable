package com.kanban.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kanban.backend.config.SecurityConfig;
import com.kanban.backend.mapper.Mapper;
import com.kanban.backend.model.Table;
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

@WebMvcTest({TaskGroupController.class, AuthController.class})
@Import({SecurityConfig.class, TokenService.class})
class TaskGroupControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @MockBean
    private TaskService taskService;
    @MockBean
    private TableService tableService;
    @MockBean
    private TaskGroupService taskGroupService;
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
        this.mvc.perform(get("/taskgroups")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void shouldGetAllTaskGroups() throws Exception {
        //given
        final List<TaskGroup> taskGroups = List.of(new TaskGroup());

        //when
        when(this.taskGroupService.getAllTaskGroups()).thenReturn(taskGroups);

        //then
        this.mvc.perform(get("/taskgroups"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldGetTaskGroupById() throws Exception {
        //given
        final Long id = 21L;
        final TaskGroup taskGroup = new TaskGroup();

        //when
        when(this.taskGroupService.getTaskGroupById(id)).thenReturn(taskGroup);

        //then
        this.mvc.perform(get("/taskgroups/{id}", id)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldAddTaskGroup() throws Exception {
        //given
        final TaskGroup taskGroup = new TaskGroup("yg359YdVdX2n", new Table(), Collections.emptyList());

        //when
        when(this.taskGroupService.addTaskGroup(taskGroup)).thenReturn(taskGroup);

        //then
        this.mvc.perform(post("/taskgroups")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(taskGroup))).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldDeleteTaskGroupById() throws Exception {
        //given
        final Long id = 23L;
        final TaskGroup taskGroup = new TaskGroup("Ub7hUjhPW71wP6", new Table(), Collections.emptyList());

        //when
        when(this.taskGroupService.deleteTaskGroupById(id)).thenReturn(taskGroup);

        //then
        this.mvc.perform(delete("/taskgroups/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(taskGroup))).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldUpdateTask() throws Exception {
        //given
        final Long id = 12L;
        final TaskGroup taskGroup = new TaskGroup(id, "8bBIa73U6", new Table(), Collections.emptyList());

        //when
        when(this.taskGroupService.updateTaskGroup(id, taskGroup)).thenReturn(taskGroup);

        //then
        this.mvc.perform(put("/taskgroups/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(taskGroup))).andExpect(status().isOk());
    }

}
