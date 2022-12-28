package com.kanban.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "TAG_TABLE")
public class Tag {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;

    // Relations
    @ManyToMany(mappedBy = "TASK_TABLE")
    private List<Task> tasks;
}
