package com.kanban.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kanban.backend.config.SecurityConfig;
import com.kanban.backend.mapper.Mapper;
import com.kanban.backend.model.Table;
import com.kanban.backend.repository.UserRepository;
import com.kanban.backend.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({TableController.class, AuthController.class})
@Import({SecurityConfig.class, TokenService.class})
class TableControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private TaskGroupService taskGroupService;
    @MockBean
    private TableService tableService;
    @MockBean
    private UserService userService;
    @MockBean
    private UserTableRoleService userTableRoleService;
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
        this.mvc.perform(get("/tables")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void shouldGetAllTables() throws Exception {
        //given
        final List<Table> tables = List.of(new Table());

        //when
        when(this.tableService.getAllTables()).thenReturn(tables);

        //then
        this.mvc.perform(get("/tables"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldGetTableById() throws Exception {
        //given
        final Long id = 14L;
        final Table table = new Table();

        //when
        when(this.tableService.getTableById(id)).thenReturn(table);

        //then
        this.mvc.perform(get("/tables/{id}", id)).andExpect(status().isOk());
    }
}