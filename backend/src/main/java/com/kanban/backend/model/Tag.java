package com.kanban.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "T_TAG")
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

}
