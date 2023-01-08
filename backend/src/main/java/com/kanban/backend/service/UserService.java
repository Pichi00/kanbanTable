package com.kanban.backend.service;

import com.kanban.backend.model.User;
import com.kanban.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
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

        return userRepository.save(user);
    }

    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
