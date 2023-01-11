package com.kanban.backend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserCreatorDTO {
    private final String name;
    private final String email;
    private final String password;
}
