package com.kanban.backend.service;

import com.kanban.backend.exception.ResourceNotFoundException;
import com.kanban.backend.model.Task;
import com.kanban.backend.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Task.class.getSimpleName(), id));
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        Task taskToDelete = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Task.class.getSimpleName(), id));
        taskToDelete.clearTags();
        taskRepository.save(taskToDelete);
        taskRepository.deleteById(id);
    }
}
