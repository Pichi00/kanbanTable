package com.kanban.backend.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity(name = "T_TASKGROUP")
public class TaskGroup {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
