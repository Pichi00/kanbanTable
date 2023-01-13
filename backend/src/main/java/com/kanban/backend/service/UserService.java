package com.kanban.backend.service;

import com.kanban.backend.dto.UserCreatorDTO;
import com.kanban.backend.exception.ResourceNotFoundException;
import com.kanban.backend.model.Table;
import com.kanban.backend.model.User;
import com.kanban.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final TableService tableService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));
    }

    public User addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return null;
        }

        String email = user.getEmail();
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        boolean isEmailValid = Pattern
                .compile(emailRegex)
                .matcher(email)
                .matches();

        if (!isEmailValid) {
            return null;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User deleteUserById(Long id) {
        User userToDelete = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));

        for (Table table : userToDelete.getTables()) {
            tableService.deleteTableById(table.getId());
        }

        userRepository.delete(userToDelete);
        return userToDelete;
    }

    public User updateUser(Long id, UserCreatorDTO newUserDTO) {
        User userToUpdate = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));

        if (newUserDTO.getName() != null) {
            userToUpdate.setName(newUserDTO.getName());
        }

        return userRepository.save(userToUpdate);
    }
}
