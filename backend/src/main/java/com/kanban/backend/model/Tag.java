package com.kanban.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity(name = "T_TAG")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String color;

    // Relations
    @ManyToMany(mappedBy = "tags")
    @NonNull
    @JsonIgnore
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "table_id")
    @NonNull
    @JsonIgnore
    private Table table;

}
