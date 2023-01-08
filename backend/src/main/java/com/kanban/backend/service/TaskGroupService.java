package com.kanban.backend.service;

import com.kanban.backend.model.TaskGroup;
import com.kanban.backend.repository.TaskGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskGroupService {
    private final TaskGroupRepository taskGroupRepository;

    public List<TaskGroup> getAllTaskGroups() {
        return taskGroupRepository.findAll();
    }

    public TaskGroup getTaskGroupById(Long id) {
        return taskGroupRepository.findById(id).orElse(null);
    }

    public TaskGroup addTaskGroup(TaskGroup taskGroup) {
        return taskGroupRepository.save(taskGroup);
    }

    public void deleteTaskGroupById(Long id) {
        taskGroupRepository.deleteById(id);
    }
}
