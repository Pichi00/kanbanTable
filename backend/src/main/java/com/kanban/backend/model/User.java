package com.kanban.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "USER_TABLE")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;

    // Relations
    @OneToMany(mappedBy = "USER_TABLE")
    private List<Table> tables;
}
