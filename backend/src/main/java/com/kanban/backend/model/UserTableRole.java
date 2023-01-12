package com.kanban.backend.model;

import com.kanban.backend.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "T_USER_TABLE_ROLE")
public class UserTableRole {
    @Id
    @GeneratedValue
    private Long id;
    private Role role;

    // Relations
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table table;
}
