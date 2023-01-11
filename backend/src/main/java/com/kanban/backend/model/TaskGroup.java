package com.kanban.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@Entity(name = "T_TASKGROUP")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class TaskGroup {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;

    // Relations
    @ManyToOne
    @JoinColumn(name = "table_id")
    @NonNull
    @JsonIgnore
    private Table table;
    @OneToMany(mappedBy = "taskGroup", fetch = FetchType.EAGER)
    @NonNull
    private List<Task> tasks;

}
