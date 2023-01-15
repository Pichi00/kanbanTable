package com.kanban.backend.service;

import com.kanban.backend.exception.ResourceNotFoundException;
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

    public UserTableRole getUserTableRoleById(Long id) {
        return this.userTableRoleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(UserTableRole.class.getSimpleName(), id));
    }

    public UserTableRole addUserTableRole(UserTableRole userTableRole) {
        return this.userTableRoleRepository.save(userTableRole);
    }

    public UserTableRole deleteUserTableRoleById(Long id) {
        UserTableRole userTableRoleToDelete = this.getUserTableRoleById(id);
        this.userTableRoleRepository.deleteById(id);

        return userTableRoleToDelete;
    }
}
