package com.kanban.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "TASKGROUP_TABLE")
public class TaskGroup {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;

    // Relations
    @ManyToOne
    @JoinColumn(name = "ID")
    private Table table;
    @OneToMany(mappedBy = "TASKGROUP_TABLE")
    private List<Task> tasks;
}