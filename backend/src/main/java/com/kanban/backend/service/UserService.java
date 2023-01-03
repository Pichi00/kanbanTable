package com.kanban.backend.service;

import com.kanban.backend.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    // DATABASE HERE

    public List<User> getAllUsers() {
        // TODO
        return null;
    }

    public User getUserById(Long id) {
        // TODO

        return null;
    }

    public void addUser(User user) {
        // TODO
    }

    public void deleteUserById(Long id) {
        // TODO
    }
}
