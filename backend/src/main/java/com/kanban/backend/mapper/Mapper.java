package com.kanban.backend.mapper;

import com.kanban.backend.dto.TableCreatorDTO;
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

        return new UserDTO(user.getName(), user.getEmail(), tablesId);
    }

    public User toUser(UserCreatorDTO userCreatorDTO) {
        return new User(
                null,
                userCreatorDTO.getName(),
                userCreatorDTO.getEmail(),
                userCreatorDTO.getPassword(),
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    public Table toTable(TableCreatorDTO tableCreatorDTO, User owner) {
        return new Table(
                null,
                tableCreatorDTO.getName(),
                owner,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
}
