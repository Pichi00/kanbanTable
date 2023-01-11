package com.kanban.backend.controller;

import com.kanban.backend.model.Table;
import com.kanban.backend.model.User;
import com.kanban.backend.service.TableService;
import com.kanban.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;
    private final UserService userService;

    @GetMapping("/tables")
    public ResponseEntity<List<Table>> getAllTables() {
        List<Table> responseBody = tableService.getAllTables();
        return ResponseEntity.ok().body(responseBody);
    }

    @GetMapping("/tables/{id}")
    public ResponseEntity<Table> getTableById(@PathVariable Long id) {
        Table responseBody = tableService.getTableById(id);
        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping("/tables")
    public ResponseEntity<Table> addTable(@RequestBody Table table) {
        Table responseBody = tableService.addTable(table);
        return ResponseEntity.ok().body(responseBody);
    }

    @DeleteMapping("/tables/{id}")
    public ResponseEntity<Table> deleteTableById(@PathVariable Long id) {
        Table responseBody = tableService.deleteTableById(id);
        return ResponseEntity.ok().body(responseBody);
    }

    @PutMapping("tables/{tableId}/users/{userId}")
    public ResponseEntity<Table> assignOwnerToTable(@PathVariable Long tableId, @PathVariable Long userId) {
        Table table = tableService.getTableById(tableId);
        User owner = userService.getUserById(userId);

        table.setOwner(owner);
        Table responseBody = tableService.addTable(table);
        return ResponseEntity.ok().body(responseBody);
    }
}
