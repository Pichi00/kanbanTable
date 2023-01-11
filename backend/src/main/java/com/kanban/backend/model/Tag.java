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

    // Relations
    @ManyToMany(mappedBy = "tags")
    @NonNull
    @JsonIgnore
    private List<Task> tasks;

    public void clearTasks() {
        tasks.clear();
    }
}
