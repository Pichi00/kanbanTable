package com.kanban.backend.dto;

import com.kanban.backend.model.Tag;
import com.kanban.backend.model.TaskGroup;
import com.kanban.backend.model.UserTableRole;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class TableDTO {
    private final Long id;
    private final String name;
    private final List<TaskGroup> taskGroups;
    private final List<UserTableRoleDTO> userTableRoles;
    private final List<Tag> tags;
}
