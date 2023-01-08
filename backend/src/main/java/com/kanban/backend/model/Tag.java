package com.kanban.backend.model;

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
    private List<Task> tasks;
}
