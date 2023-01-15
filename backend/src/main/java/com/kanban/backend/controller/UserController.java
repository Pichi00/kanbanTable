package com.kanban.backend.controller;

import com.kanban.backend.dto.UserCreatorDTO;
import com.kanban.backend.dto.UserDTO;
import com.kanban.backend.mapper.Mapper;
import com.kanban.backend.model.User;
import com.kanban.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                .map(mapper::toUserDTO)
                .toList();

        return ResponseEntity.ok().body(responseBody);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserDTO responseBody = mapper.toUserDTO(user);
        return ResponseEntity.ok().body(responseBody);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable Long id) {
        User deletedUser = userService.deleteUserById(id);
        UserDTO responseBody = mapper.toUserDTO(deletedUser);
        return ResponseEntity.ok().body(responseBody);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
                                              @RequestBody UserCreatorDTO newUserDTO) {
        User updatedUser = userService.updateUser(id, newUserDTO);
        UserDTO responseBody = mapper.toUserDTO(updatedUser);
        return ResponseEntity.ok().body(responseBody);
    }
}
