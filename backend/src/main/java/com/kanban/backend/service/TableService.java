package com.kanban.backend.service;

import com.kanban.backend.exception.ResourceNotFoundException;
import com.kanban.backend.model.Table;
import com.kanban.backend.model.TaskGroup;
import com.kanban.backend.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;
    private final TaskGroupService taskGroupService;

    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }

    public Table getTableById(Long id) {
        return tableRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Table.class.getSimpleName(), id));
    }

    public Table addTable(Table table) {
        return tableRepository.save(table);
    }

    public Table deleteTableById(Long id) {
        Table tableToDelete = tableRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Table.class.getSimpleName(), id));

        for (TaskGroup taskGroup : tableToDelete.getTaskGroups()) {
            taskGroupService.deleteTaskGroupById(taskGroup.getId());
        }

        tableRepository.delete(tableToDelete);
        return tableToDelete;
    }
}
