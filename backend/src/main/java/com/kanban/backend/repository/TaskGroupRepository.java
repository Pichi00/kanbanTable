package com.kanban.backend.repository;

import com.kanban.backend.model.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskGroupRepository extends JpaRepository<TaskGroup, Long> {
}
