package com.kanban.backend.service;

import com.kanban.backend.model.UserTableRole;
import com.kanban.backend.repository.UserTableRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTableRoleService {
    private final UserTableRoleRepository userTableRoleRepository;

    public List<UserTableRole> getAllUserTableRoles() {
        return this.userTableRoleRepository.findAll();
    }

    public UserTableRole addUserTableRole(UserTableRole userTableRole) {
        return this.userTableRoleRepository.save(userTableRole);
    }
}
