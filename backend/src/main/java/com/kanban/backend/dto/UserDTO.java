package com.kanban.backend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private final Long id;
    private final String name;
    private final String email;
    private final List<UserTableRoleDTO> userTableRoles;
}
