package com.kanban.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @OneToMany(mappedBy = "table")
    @NonNull
    private List<TaskGroup> taskGroups;
    @OneToMany(mappedBy = "table")
    @NonNull
    private List<UserTableRole> userTableRoles;
    @OneToMany(mappedBy = "table")
    @NonNull
    private List<Tag> tags;
}
