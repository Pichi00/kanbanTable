package com.kanban.backend.controller;

import com.kanban.backend.dto.UserCreatorDTO;
import com.kanban.backend.dto.UserDTO;
import com.kanban.backend.mapper.Mapper;
import com.kanban.backend.model.Table;
import com.kanban.backend.model.User;
import com.kanban.backend.service.TableService;
import com.kanban.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TableService tableService;
    private final Mapper mapper;

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService
                .getAllUsers()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        return mapper.toDTO(user);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody UserCreatorDTO userCreatorDTO) {
        User user = mapper.toUser(userCreatorDTO);
        if(userCreatorDTO.getTables() != null){
            userCreatorDTO
                    .getTables()
                    .stream()
                    .map(tableService::getTableById)
                    .forEach(user::addTable);
        }


        return userService.addUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
