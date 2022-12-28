package com.kanban.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Table {
    @Id
    @GeneratedValue
    private Long id;
}
