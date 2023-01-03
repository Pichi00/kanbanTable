package com.kanban.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

}
