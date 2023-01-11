package com.kanban.backend.controller;

import com.kanban.backend.model.Tag;
import com.kanban.backend.model.Task;
import com.kanban.backend.service.TagService;
import com.kanban.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final TagService tagService;

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
    }

    @PutMapping("tasks/{taskId}/tags/{tagId}")
    public Task addTagToTask(@PathVariable Long taskId, @PathVariable Long tagId) {
        Task task = taskService.getTaskById(taskId);
        Tag tag = tagService.getTagById(tagId);
        task.addTag(tag);
        return taskService.addTask(task);
    }
}
