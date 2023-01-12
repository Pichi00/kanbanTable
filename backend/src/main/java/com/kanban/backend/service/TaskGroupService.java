package com.kanban.backend.service;

import com.kanban.backend.exception.ResourceNotFoundException;
import com.kanban.backend.model.Task;
import com.kanban.backend.model.TaskGroup;
import com.kanban.backend.repository.TaskGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskGroupService {
    private final TaskGroupRepository taskGroupRepository;
    private final TaskService taskService;

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

        for (Task task : taskGroupToDelete.getTasks()) {
            taskService.deleteTaskById(task.getId());
        }

        taskGroupRepository.delete(taskGroupToDelete);
        return taskGroupToDelete;
    }

    public TaskGroup updateTaskGroup(Long id, TaskGroup newTaskGroup) {
        TaskGroup taskGroupToUpdate = taskGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TaskGroup.class.getSimpleName(), id));

        if (newTaskGroup.getName() != null) {
            taskGroupToUpdate.setName(newTaskGroup.getName());
        }

        return taskGroupRepository.save(taskGroupToUpdate);
    }
}
