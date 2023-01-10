package com.kanban.backend.service;

import com.kanban.backend.config.UserCustomDetails;
import com.kanban.backend.model.User;
import com.kanban.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.getUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        return new UserCustomDetails(user);
    }
}
