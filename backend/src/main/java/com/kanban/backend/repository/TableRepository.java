package com.kanban.backend.repository;

import com.kanban.backend.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table, Long> {

}
