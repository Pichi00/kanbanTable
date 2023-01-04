package com.kanban.backend.controller;

import com.kanban.backend.model.Table;
import com.kanban.backend.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;

    @GetMapping("/tables")
    public List<Table> getAllTables() {
        return tableService.getAllTables();
    }

    @GetMapping("/tables/{id}")
    public Table getTableById(@PathVariable Long id) {
        return tableService.getTableById(id);
    }

    @PostMapping("/tables")
    public void addTable(@RequestBody Table table) {
        tableService.addTable(table);
    }

    @DeleteMapping("/tables/{id}")
    public void deleteTableById(@PathVariable Long id) {
        tableService.deleteTableById(id);
    }
}
