package com.kanban.backend.dto;

import com.kanban.backend.model.TaskGroup;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class TableDTO {
    private final String name;
    private final List<Long> taskGroupsId;
    private final List<Long> userTableRolesId;
}
