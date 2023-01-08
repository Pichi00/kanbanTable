package com.kanban.backend.service;

import com.kanban.backend.model.Table;
import com.kanban.backend.model.User;
import com.kanban.backend.repository.UserRepository;
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
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    private UserService subject;

    @BeforeEach
    void setUp(){
        this.subject = new UserService(this.userRepository);
    }

    @Test
    void shouldGetAllUsers() {
        //when
        this.subject.getAllUsers();

        //then
        verify(this.userRepository).findAll();
    }

    @Test
    void shouldGetUserById() {
        //given
        final Long id = 1L;
        final User returnedOutput = new User(1L, "1rKLDEh4UL", "hn2DnX8DK20@xdd.com", "73i37CS0EZTzV", List.of(new Table()));
        given(this.userRepository.findById(id)).willReturn(Optional.of(returnedOutput));

        //when
        this.subject.getUserById(id);

        //then
        verify(this.userRepository).findById(id);
    }

    @Test
    void shouldAddUser() {
        //given
        final User givenInput = new User("YOWfbtD7reL67l","9tgVKZXW6@visit.com", "DRgRNzgI", List.of(new Table()));


        //when
        this.subject.addUser(givenInput);

        //then
        final ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(this.userRepository).save(userArgumentCaptor.capture());
        final User capturedUser = userArgumentCaptor.getValue();

        AssertionsForClassTypes.assertThat(capturedUser).isEqualTo(givenInput);
    }


    @Test
    void shouldDeleteUser() {
        //given
        final Long id = 1L;

        //when
        this.subject.deleteUserById(id);

        //then
        verify(this.userRepository).deleteById(id);
    }
}