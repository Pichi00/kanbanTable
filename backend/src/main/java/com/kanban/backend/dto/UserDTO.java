package com.kanban.backend.dto;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserDTO {
    private final String name;
    private final String email;
    private final List<Long> tablesId;
}
