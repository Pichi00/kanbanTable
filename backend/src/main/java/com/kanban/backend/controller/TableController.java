package com.kanban.backend.controller;

import com.kanban.backend.model.Table;
import com.kanban.backend.model.User;
import com.kanban.backend.service.TableService;
import com.kanban.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;
    private final UserService userService;

    @GetMapping("/tables")
    public List<Table> getAllTables() {
        return tableService.getAllTables();
    }

    @GetMapping("/tables/{id}")
    public Table getTableById(@PathVariable Long id) {
        return tableService.getTableById(id);
    }

    @PostMapping("/tables")
    public Table addTable(@RequestBody Table table) {
        return tableService.addTable(table);
    }

    @DeleteMapping("/tables/{id}")
    public void deleteTableById(@PathVariable Long id) {
        tableService.deleteTableById(id);
    }

    @PutMapping("tables/{tableId}/users/{userId}")
    public Table assignOwnerToTable(@PathVariable Long tableId, @PathVariable Long userId) {
        Table table = tableService.getTableById(tableId);
        User owner = userService.getUserById(userId);

        table.setOwner(owner);
        return tableService.addTable(table);
    }
}
