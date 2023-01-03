package com.kanban.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "T_TASK")
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

}
