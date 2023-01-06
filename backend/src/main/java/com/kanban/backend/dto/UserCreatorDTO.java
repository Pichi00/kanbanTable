package com.kanban.backend.dto;

import com.kanban.backend.model.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class UserCreatorDTO {
    private final String name;
    private final String email;
    private final String password;
    private final List<Long> tables;
}
