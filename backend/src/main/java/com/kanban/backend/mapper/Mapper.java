package com.kanban.backend.mapper;

import com.kanban.backend.dto.UserCreatorDTO;
import com.kanban.backend.dto.UserDTO;
import com.kanban.backend.model.Table;
import com.kanban.backend.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Mapper {
    public UserDTO toDTO(User user) {
        List<Long> tablesId = Collections.emptyList();
        if (user.getTables().size() > 0) {
            tablesId = user.getTables()
                    .stream()
                    .map(Table::getId)
                    .toList();
        }

        // redundant
        UserDTO result = new UserDTO(user.getName(), user.getEmail(), tablesId);
        return result;
    }

    public User toUser(UserCreatorDTO userCreatorDTO) {
        return new User(
                null,
                userCreatorDTO.getName(),
                userCreatorDTO.getEmail(),
                userCreatorDTO.getPassword(),
                new ArrayList<>()
        );
    }
}
