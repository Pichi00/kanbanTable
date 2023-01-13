package com.kanban.backend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private final String name;
    private final String email;
    private final List<Long> tablesId;
    private final List<Long> userTableRolesId;
}
