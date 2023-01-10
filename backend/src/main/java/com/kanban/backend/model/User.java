package com.kanban.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity(name = "T_USER")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;

    // Relations
    @OneToMany(mappedBy = "owner")
    @NonNull
    private List<Table> tables;

    public void addTable(Table table) {
        tables.add(table);
    }
}
