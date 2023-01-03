package com.kanban.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Entity(name = "T_TASKGROUP")
@AllArgsConstructor
@NoArgsConstructor
public class TaskGroup {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    // Relations
    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table table;
    @OneToMany(mappedBy = "taskGroup")
    private List<Task> tasks;
}
