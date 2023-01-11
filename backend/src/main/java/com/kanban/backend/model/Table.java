package com.kanban.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity(name = "T_TABLE")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Table {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;

    // Relations
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @NonNull
    @JsonIgnore
    private User owner;

    @OneToMany(mappedBy = "table")
    @NonNull
    private List<TaskGroup> taskGroups;
}
