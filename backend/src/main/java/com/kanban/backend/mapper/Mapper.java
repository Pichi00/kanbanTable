package com.kanban.backend.mapper;

import com.kanban.backend.dto.*;
import com.kanban.backend.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Mapper {
    public UserDTO toUserDTO(User user) {
        List<Long> userTableRolesId = Collections.emptyList();
        if (user.getUserTableRoles().size() > 0) {
            userTableRolesId = user.getUserTableRoles()
                    .stream()
                    .map(UserTableRole::getId)
                    .toList();
        }

        return new UserDTO(user.getName(), user.getEmail(), userTableRolesId);
    }

    public User toUser(UserCreatorDTO userCreatorDTO) {
        return new User(
                null,
                userCreatorDTO.getName(),
                userCreatorDTO.getEmail(),
                userCreatorDTO.getPassword(),
                new ArrayList<>()
        );
    }

    public TableDTO toTableDTO(Table table) {
        List<TaskGroup> taskGroups = Collections.emptyList();
        if (table.getTaskGroups().size() > 0) {
            taskGroups = table.getTaskGroups();
        }

        List<UserTableRoleDTO> userTableRoles = Collections.emptyList();
        if (table.getUserTableRoles().size() > 0) {
            userTableRoles = table.getUserTableRoles()
                    .stream()
                    .map(this::toUserTableRoleDTO)
                    .toList();
        }

        List<Tag> tags = Collections.emptyList();
        if (table.getTags().size() > 0) {
            tags = table.getTags();
        }

        return new TableDTO(table.getId(), table.getName(), taskGroups, userTableRoles, tags);
    }

    public Table toTable(TableCreatorDTO tableCreatorDTO) {
        return new Table(
                null,
                tableCreatorDTO.getName(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    public UserTableRoleDTO toUserTableRoleDTO(UserTableRole userTableRole) {
        return new UserTableRoleDTO(
                userTableRole.getId(),
                userTableRole.getRole(),
                userTableRole.getUser().getId(),
                userTableRole.getTable().getId()
        );
    }
}
