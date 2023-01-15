package com.kanban.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kanban.backend.config.SecurityConfig;
import com.kanban.backend.dto.UserCreatorDTO;
import com.kanban.backend.mapper.Mapper;
import com.kanban.backend.model.User;
import com.kanban.backend.repository.UserRepository;
import com.kanban.backend.service.TableService;
import com.kanban.backend.service.TokenService;
import com.kanban.backend.service.UserService;
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

@WebMvcTest({UserController.class, AuthController.class})
@Import({SecurityConfig.class, TokenService.class})
class UserControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private UserService userService;
    @MockBean
    private TableService tableService;
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
        this.mvc.perform(get("/users")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void shouldGetAllUsers() throws Exception {
        //given
        final List<User> users = List.of(new User());

        //when
        when(this.userService.getAllUsers()).thenReturn(users);

        //then
        this.mvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldGetUserById() throws Exception {
        //given
        final Long id = 10L;
        final User user = new User();

        //when
        when(this.userService.getUserById(id)).thenReturn(user);

        //then
        this.mvc.perform(get("/users/{id}", id)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldDeleteUserById() throws Exception {
        //given
        final Long id = 3L;
        final User user = new User("ARt0R8tRU", "hnMnmlZW@Okdsj.com", "9072lDBm9", Collections.emptyList());

        //when
        when(this.userService.deleteUserById(id)).thenReturn(user);

        //then
        this.mvc.perform(delete("/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(user))).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldUpdateUser() throws Exception {
        //given
        final Long id = 29L;
        final User user = new User("NecwNBxv4", "YQxIE@kJDiasa.com", "cQuOm", Collections.emptyList());
        final UserCreatorDTO userCreatorDTO = new UserCreatorDTO("NecwNBxv4", "YQxIE@kJDiasa.com", "cQuOm");

        //when
        when(this.userService.updateUser(id, userCreatorDTO)).thenReturn(user);

        //then
        this.mvc.perform(put("/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(userCreatorDTO))).andExpect(status().isOk());
    }
}
