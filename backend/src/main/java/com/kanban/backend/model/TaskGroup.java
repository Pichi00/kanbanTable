package com.kanban.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "T_TASKGROUP")
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
    @OneToMany(mappedBy = "T_TASKGROUP")
    private List<Task> tasks;
}
