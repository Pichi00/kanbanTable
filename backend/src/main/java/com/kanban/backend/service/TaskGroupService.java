package com.kanban.backend.service;

import com.kanban.backend.exception.ResourceNotFoundException;
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
        return taskGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TaskGroup.class.getSimpleName(), id));
    }

    public TaskGroup addTaskGroup(TaskGroup taskGroup) {
        return taskGroupRepository.save(taskGroup);
    }

    public TaskGroup deleteTaskGroupById(Long id) {
        TaskGroup taskGroupToDelete = taskGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TaskGroup.class.getSimpleName(), id));
        taskGroupRepository.delete(taskGroupToDelete);
        return taskGroupToDelete;
    }
}
