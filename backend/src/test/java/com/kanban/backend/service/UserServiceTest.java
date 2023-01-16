package com.kanban.backend.service;

import com.kanban.backend.dto.UserCreatorDTO;
import com.kanban.backend.exception.ResourceNotFoundException;
import com.kanban.backend.model.User;
import com.kanban.backend.repository.UserRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private TableService tableService;
    @Mock
    private UserTableRoleService userTableRoleService;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService subject;

    @BeforeEach
    void setUp() {
        this.subject = new UserService(this.userRepository, this.tableService, this.userTableRoleService, this.passwordEncoder);
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
        final User returnedOutput = new User(1L, "1rKLDEh4UL", "hn2DnX8DK20@xdd.com", "73i37CS0EZTzV", Collections.emptyList());
        given(this.userRepository.findById(id)).willReturn(Optional.of(returnedOutput));

        //when
        this.subject.getUserById(id);

        //then
        verify(this.userRepository).findById(id);
    }

    @Test
    void shouldAddUser() {
        //given
        String userPassword = "07g7I6il8a";
        String userEncodedPassword = "9FEw3F1yMfIWa";

        final User userToAdd = new User("YOWfbtD7reL67l", "9tgVKZXW6@visit.com", userPassword, Collections.emptyList());
        given(this.userRepository.save(userToAdd)).willReturn(userToAdd);
        given(this.passwordEncoder.encode(userToAdd.getPassword())).willReturn(userEncodedPassword);

        //when
        final User expectedOutput = this.subject.addUser(userToAdd);

        //then
        final ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(this.userRepository).save(userArgumentCaptor.capture());
        final User capturedUser = userArgumentCaptor.getValue();

        AssertionsForClassTypes.assertThat(capturedUser).isEqualTo(expectedOutput);
    }


    @Test
    void shouldDeleteUser() {
        //given
        final Long id = 1L;
        final User userToDelete = new User(id, "33urZ75S", "FIgU78r@dHflsj.com", "ZdNH33", Collections.emptyList());
        given(this.userRepository.findById(id)).willReturn(Optional.of(userToDelete));

        //when
        this.subject.deleteUserById(id);

        //then
        verify(this.userRepository).delete(userToDelete);
    }

    @Test
    void shouldThrowNotFoundException() {
        //given
        final Long id = 14L;
        final UserCreatorDTO user = new UserCreatorDTO("MZq3l5jarfvxv", "cqU5wwNl6oYdKo@jksDkgd.com", "9zfysgxcDf");
        String expectedMessage = "Could not find User with id " + id.toString();
        given(this.userRepository.findById(id)).willThrow(new ResourceNotFoundException(User.class.getSimpleName(), id));

        //then
        assertThatThrownBy(() -> this.subject.getUserById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(expectedMessage);

        assertThatThrownBy(() -> this.subject.deleteUserById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(expectedMessage);

        assertThatThrownBy(() -> this.subject.updateUser(id, user))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(expectedMessage);
    }
}
