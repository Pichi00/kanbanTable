package com.kanban.backend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserTableRoleDTO {
    private final Long id;
    private final String role;
    private final Long userId;
    private final Long tableId;

}
