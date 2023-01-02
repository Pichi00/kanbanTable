package com.kanban.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "T_USER")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;

    // Relations
    @OneToMany(mappedBy = "T_USER")
    private List<Table> tables;
}
