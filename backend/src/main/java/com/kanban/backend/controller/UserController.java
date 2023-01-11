package com.kanban.backend.controller;

import com.kanban.backend.dto.UserDTO;
import com.kanban.backend.mapper.Mapper;
import com.kanban.backend.model.User;
import com.kanban.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final Mapper mapper;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> responseBody = userService
                .getAllUsers()
                .stream()
                .map(mapper::toDTO)
                .toList();

        return ResponseEntity.ok().body(responseBody);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserDTO responseBody = mapper.toDTO(user);
        return ResponseEntity.ok().body(responseBody);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable Long id) {
        User deletedUser = userService.deleteUserById(id);
        UserDTO responseBody = mapper.toDTO(deletedUser);
        return ResponseEntity.ok().body(responseBody);
    }
}
