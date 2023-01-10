package com.kanban.backend.controller;

import com.kanban.backend.dto.UserCreatorDTO;
import com.kanban.backend.dto.UserDTO;
import com.kanban.backend.mapper.Mapper;
import com.kanban.backend.model.User;
import com.kanban.backend.service.TableService;
import com.kanban.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TableService tableService;
    private final Mapper mapper;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService
                .getAllUsers()
                .stream()
                .map(mapper::toDTO)
                .toList();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(mapper.toDTO(user), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody UserCreatorDTO userCreatorDTO) {
        User user = mapper.toUser(userCreatorDTO);

        if (userCreatorDTO.getTablesId() != null) {
            userCreatorDTO
                    .getTablesId()
                    .stream()
                    .map(tableService::getTableById)
                    .forEach(user::addTable);
        }

        User userResponse = userService.addUser(user);

        if (userResponse == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        if (userService.deleteUserById(id)) {
            return new ResponseEntity<>("User deleted!", HttpStatus.OK);
        }

        return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
    }
}
