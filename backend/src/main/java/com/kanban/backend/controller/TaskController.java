package com.kanban.backend.controller;

import com.kanban.backend.model.Tag;
import com.kanban.backend.model.Task;
import com.kanban.backend.model.TaskGroup;
import com.kanban.backend.service.TagService;
import com.kanban.backend.service.TaskGroupService;
import com.kanban.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final TaskGroupService taskGroupService;
    private final TagService tagService;

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> responseBody = taskService.getAllTasks();
        return ResponseEntity.ok().body(responseBody);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task responseBody = taskService.getTaskById(id);
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task responseBody = taskService.addTask(task);
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> deleteTaskById(@PathVariable Long id) {
        Task responseBody = taskService.deleteTaskById(id);
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("tasks/{taskId}/tags/{tagId}")
    public ResponseEntity<Task> addTagToTask(@PathVariable Long taskId, @PathVariable Long tagId) {
        Task task = taskService.getTaskById(taskId);
        Tag tag = tagService.getTagById(tagId);

        task.addTag(tag);
        Task responseBody = taskService.addTask(task);
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("tasks/{taskId}/taskgroups/{taskGroupId}")
    public ResponseEntity<Task> assignTaskToTaskGroup(@PathVariable Long taskId, @PathVariable Long taskGroupId) {
        Task task = taskService.getTaskById(taskId);
        TaskGroup taskGroup = taskGroupService.getTaskGroupById(taskGroupId);

        task.setTaskGroup(taskGroup);
        Task responseBody = taskService.addTask(task);
        return ResponseEntity.ok(responseBody);
    }
}
