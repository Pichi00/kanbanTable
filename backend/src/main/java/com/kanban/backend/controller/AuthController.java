package com.kanban.backend.controller;

import com.kanban.backend.dto.UserCreatorDTO;
import com.kanban.backend.mapper.Mapper;
import com.kanban.backend.model.User;
import com.kanban.backend.service.TableService;
import com.kanban.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final TableService tableService;
    private final Mapper mapper;

    @GetMapping("/auth/register")
    public String test() {
        return "XD";
    }

    @PostMapping("/auth/register")
    public ResponseEntity<User> register(@RequestBody UserCreatorDTO userCreatorDTO) {
        User user = mapper.toUser(userCreatorDTO);
        User userResponse = userService.addUser(user);

        if (userResponse == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
