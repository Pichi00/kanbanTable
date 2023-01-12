package com.kanban.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "T_USER_TABLE_ROLE")
public class UserTableRole {
    @Id
    @GeneratedValue
    private Long id;
    private String role;

    // Relations
    @NonNull
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;
    @NonNull
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "table_id")
    private Table table;
}
