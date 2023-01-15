package com.kanban.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kanban.backend.config.SecurityConfig;
import com.kanban.backend.mapper.Mapper;
import com.kanban.backend.model.Tag;
import com.kanban.backend.repository.UserRepository;
import com.kanban.backend.service.TagService;
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

@WebMvcTest({TagController.class, AuthController.class})
@Import({SecurityConfig.class, TokenService.class})
class TagControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
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
        this.mvc.perform(get("/tags")).andExpect(status().isUnauthorized());

    }

    @Test
    @WithMockUser
    void shouldGetAllTags() throws Exception {
        //given
        final List<Tag> tags = List.of(new Tag());

        //when
        when(this.tagService.getAllTags()).thenReturn(tags);

        //then
        this.mvc.perform(get("/tags"))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    void shouldGetTagById() throws Exception {
        //given
        final Long id = 30L;
        final Tag tag = new Tag();

        //when
        when(this.tagService.getTagById(id)).thenReturn(tag);

        //then
        this.mvc.perform(get("/tags/30")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldAddTag() throws Exception {
        //given
        final Tag tag = new Tag("AL5KZm8ezRA6", Collections.emptyList());

        //when
        when(this.tagService.addTag(tag)).thenReturn(tag);

        //then
        this.mvc.perform(post("/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(tag))).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldDeleteTagById() throws Exception {
        //given
        final Long id = 28L;
        final Tag tag = new Tag(id, "4j2Po", Collections.emptyList());

        //when
        when(this.tagService.deleteTagById(id)).thenReturn(tag);

        //then
        this.mvc.perform(delete("/tags/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(tag))).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldUpdateTag() throws Exception {
        //given
        final Long id = 6L;
        final Tag tag = new Tag(id, "NLvQ27cg1UpmxV", Collections.emptyList());

        //when
        when(this.tagService.updateTag(id, tag)).thenReturn(tag);

        //then
        this.mvc.perform(put("/tags/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(tag))).andExpect(status().isOk());
    }
}