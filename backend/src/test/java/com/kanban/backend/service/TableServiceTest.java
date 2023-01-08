package com.kanban.backend.service;

import com.kanban.backend.model.Table;
import com.kanban.backend.model.TaskGroup;
import com.kanban.backend.model.User;
import com.kanban.backend.repository.TableRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TableServiceTest {

    @Mock
    private TableRepository tableRepository;

    private TableService subject;

    @BeforeEach
    void setUp(){
        this.subject = new TableService(this.tableRepository);
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
        final Long id = 1L;
        final Table returnedOutput = new Table(1L, "9NSTM",new User(), List.of(new TaskGroup()));
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
        final Table givenInput = new Table("XkRUHwn7", user,taskGroups);

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
        final Long id = 1L;

        //when
        this.subject.deleteTableById(id);

        //then
        verify(this.tableRepository).deleteById(id);
    }
}
