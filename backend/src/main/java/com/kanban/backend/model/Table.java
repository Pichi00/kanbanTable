package com.kanban.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "T_TABLE")
public class Table {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
