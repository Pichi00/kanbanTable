package com.kanban.backend.service;

import com.kanban.backend.exception.ResourceNotFoundException;
import com.kanban.backend.model.Table;
import com.kanban.backend.model.Tag;
import com.kanban.backend.model.TaskGroup;
import com.kanban.backend.model.UserTableRole;
import com.kanban.backend.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;
    private final TaskGroupService taskGroupService;
    private final TagService tagService;
    private final UserTableRoleService userTableRoleService;

    public List<Table> getAllTables() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        Long userId = jwt.getClaim("id");

        return this.userTableRoleService
                .getAllUserTableRoles()
                .stream()
                .filter(utr -> Objects.equals(utr.getUser().getId(), userId))
                .map(UserTableRole::getTable)
                .toList();
    }

    public Table getTableById(Long id) {
        return tableRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(Table.class.getSimpleName(), id));
    }

    public Table addTable(Table table) {
        return tableRepository.save(table);
    }

    public Table deleteTableById(Long id) {
        Table tableToDelete = tableRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(Table.class.getSimpleName(), id));

        for (TaskGroup taskGroup : tableToDelete.getTaskGroups()) {
            taskGroupService.deleteTaskGroupById(taskGroup.getId());
        }

        for (UserTableRole role : tableToDelete.getUserTableRoles()) {
            userTableRoleService.deleteUserTableRoleById(role.getId());
        }

        for (Tag tag : tableToDelete.getTags()) {
            tagService.deleteTagById(tag.getId());
        }

        tableRepository.delete(tableToDelete);
        return tableToDelete;
    }

    public Table updateTable(Long id, Table newTable) {
        Table tableToUpdate = tableRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(Table.class.getSimpleName(), id));

        if (newTable.getName() != null) {
            tableToUpdate.setName(newTable.getName());
        }

        return tableRepository.save(tableToUpdate);
    }
}
