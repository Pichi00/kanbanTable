package com.kanban.backend.service;

import com.kanban.backend.model.Table;
import com.kanban.backend.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;

    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }

    public Table getTableById(Long id) {
        return tableRepository.findById(id).orElse(null);
    }

    public void addTable(Table table) {
        tableRepository.save(table);
    }

    public void deleteTableById(Long id) {
        tableRepository.deleteById(id);
    }
}
