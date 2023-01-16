package com.kanban.backend.service;

import com.kanban.backend.exception.ResourceNotFoundException;
import com.kanban.backend.model.Table;
import com.kanban.backend.model.TaskGroup;
import com.kanban.backend.model.User;
import com.kanban.backend.repository.TableRepository;
import com.kanban.backend.repository.TaskGroupRepository;
import com.kanban.backend.repository.TaskRepository;
import com.kanban.backend.repository.UserTableRoleRepository;
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
class TableServiceTest {

    @Mock
    private TableRepository tableRepository;

    @Mock
    private TaskGroupRepository taskGroupRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserTableRoleRepository userTableRoleRepository;


    private TableService subject;

    @BeforeEach
    void setUp() {
        TaskGroupService taskGroupService = new TaskGroupService(this.taskGroupRepository, new TaskService(this.taskRepository));
        UserTableRoleService userTableRoleService = new UserTableRoleService(this.userTableRoleRepository);
        this.subject = new TableService(this.tableRepository, taskGroupService, userTableRoleService);
    }

    @Test
    void shouldGetAllTables() {
        //when
        this.subject.getAllTables();

        //then
        verify(this.tableRepository).findAll();
    }

    @Test
    void shouldGetTableById() {
        //given
        final Long id = 24L;
        final Table returnedOutput = new Table(id, "9NSTM", Collections.emptyList(), Collections.emptyList());
        given(this.tableRepository.findById(id)).willReturn(Optional.of(returnedOutput));

        //when
        this.subject.getTableById(id);

        //then
        verify(this.tableRepository).findById(id);
    }

    @Test
    void shouldAddTable() {
        //given
        final User user = new User();
        final List<TaskGroup> taskGroups = List.of(new TaskGroup());
        final Table givenInput = new Table("XkRUHwn7", Collections.emptyList(), Collections.emptyList());

        //when
        this.subject.addTable(givenInput);

        //then
        final ArgumentCaptor<Table> tableArgumentCaptor = ArgumentCaptor.forClass(Table.class);
        verify(this.tableRepository).save(tableArgumentCaptor.capture());
        final Table capturedTable = tableArgumentCaptor.getValue();

        AssertionsForClassTypes.assertThat(capturedTable).isEqualTo(givenInput);
    }


    @Test
    void shouldDeleteTableById() {
        //given
        final Long id = 10L;
        final Table table = new Table(id, "59R4Ohw", Collections.emptyList(), Collections.emptyList());
        given(tableRepository.findById(id)).willReturn(Optional.of(table));

        //when
        this.subject.deleteTableById(id);

        //then
        verify(this.tableRepository).delete(table);
    }

    @Test
    void shouldThrowNotFoundException() {
        //given
        final Long id = 15L;
        final Table table = new Table(id, "MZq3l5jarfvxv", Collections.emptyList(), Collections.emptyList());
        String expectedMessage = "Could not find Table with id " + id.toString();
        given(this.tableRepository.findById(id)).willThrow(new ResourceNotFoundException(Table.class.getSimpleName(), id));

        //then
        assertThatThrownBy(() -> this.subject.getTableById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(expectedMessage);

        assertThatThrownBy(() -> this.subject.deleteTableById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(expectedMessage);

        assertThatThrownBy(() -> this.subject.updateTable(id, table))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(expectedMessage);
    }
}
