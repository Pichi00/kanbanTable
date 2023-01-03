package com.kanban.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity(name = "T_TABLE")
@AllArgsConstructor
@NoArgsConstructor
public class Table {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    // Relations
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @OneToMany(mappedBy = "table")
    private List<TaskGroup> taskGroups;
}
