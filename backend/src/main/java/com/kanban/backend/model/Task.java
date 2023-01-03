package com.kanban.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity(name = "T_TASK")
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    // Relations
    @ManyToOne
    @JoinColumn(name = "taskgroup_id")
    private TaskGroup taskGroup;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "task_tag",
            joinColumns = @JoinColumn(
                    name = "tag_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "task_id",
                    referencedColumnName = "id"
            )
        )
    private List<Tag> tags;
}
