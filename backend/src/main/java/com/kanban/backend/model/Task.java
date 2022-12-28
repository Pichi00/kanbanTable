package com.kanban.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "TASK_TABLE")
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;

    // Relations
    @ManyToOne
    @JoinColumn(name = "ID")
    private TaskGroup taskGroup;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "TAG_TABLE",
            joinColumns = @JoinColumn(
                  name = "ID",
                  referencedColumnName = "ID"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "ID",
                    referencedColumnName = "ID"
            )
    )
    private List<Tag> tags;
}
