package com.kanban.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String email;
    @Column
    private String username;
    @Column
    private String password;
}