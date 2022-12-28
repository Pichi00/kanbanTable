package com.kanban.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "TABLE_TABLE")
public class Table {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;

    // Relations
    @ManyToOne
    @JoinColumn(name = "ID")
    private User user;
    @OneToMany(mappedBy = "TABLE_TABLE")
    private List<TaskGroup> taskGroups;
}
