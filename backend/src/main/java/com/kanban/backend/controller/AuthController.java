package com.kanban.backend.controller;

import com.kanban.backend.dto.UserCreatorDTO;
import com.kanban.backend.dto.UserDTO;
import com.kanban.backend.mapper.Mapper;
import com.kanban.backend.model.LoginRequest;
import com.kanban.backend.model.User;
import com.kanban.backend.service.TokenService;
import com.kanban.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final TokenService tokenService;
    private final Mapper mapper;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserCreatorDTO userCreatorDTO) {
        User user = mapper.toUser(userCreatorDTO);
        UserDTO userResponse = this.mapper.toUserDTO(userService.addUser(user));

        if (userResponse == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping("/auth/token")
    public ResponseEntity<String> token(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));

        String responseBody = this.tokenService.generateToken(authentication);
        return ResponseEntity.ok().body(responseBody);
    }
}
