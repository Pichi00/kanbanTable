package com.kanban.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "T_TAG")
public class Tag {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;

    // Relations
    @ManyToMany(mappedBy = "T_TASK")
    private List<Task> tasks;
}
