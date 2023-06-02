package com.kanban.backend.controller;

import com.kanban.backend.model.Tag;
import com.kanban.backend.model.Task;
import com.kanban.backend.model.TaskGroup;
import com.kanban.backend.model.User;
import com.kanban.backend.service.TagService;
import com.kanban.backend.service.TaskGroupService;
import com.kanban.backend.service.TaskService;
import com.kanban.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final TaskGroupService taskGroupService;
    private final TagService tagService;

    private final UserService userService;

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
    public ResponseEntity<Task> addTask(@RequestBody Task task,
                                        @RequestParam(defaultValue = "0") Long taskGroup,
                                        @RequestParam(defaultValue = "0") List<Long> tags) {

        if (taskGroup != 0) {
            TaskGroup taskGroupToAssign = taskGroupService.getTaskGroupById(taskGroup);
            task.setTaskGroup(taskGroupToAssign);
        }
        if (!tags.contains(0L)) {
            List<Tag> tagsToAssign = new ArrayList<>();
            for (Long tagId : tags) {
                Tag tag = tagService.getTagById(tagId);
                tagsToAssign.add(tag);
            }
            task.setTags(tagsToAssign);
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        task.setCreatedDate(dateFormat.format(new Date()));

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        Long userId = jwt.getClaim("id");
        User currentUser = this.userService.getUserById(userId);
        task.setCreatorUsername(currentUser.getName());

        Task responseBody = taskService.addTask(task);
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> deleteTaskById(@PathVariable Long id) {
        Task responseBody = taskService.deleteTaskById(id);
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,
                                           @RequestBody Task newTask) {
        Task responseBody = taskService.updateTask(id, newTask);
        return ResponseEntity.ok().body(responseBody);
    }

    @PutMapping("/tasks/{taskId}/tags/{tagId}")
    public ResponseEntity<Task> addTagToTask(@PathVariable Long taskId,
                                             @PathVariable Long tagId) {
        Task task = taskService.getTaskById(taskId);
        Tag tag = tagService.getTagById(tagId);

        if (!task.getTags().contains(tag)) {
            task.addTag(tag);
        }

        Task responseBody = taskService.addTask(task);
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/tasks/{taskId}/tags/{tagId}")
    public ResponseEntity<Task> removeTagFromTask(@PathVariable Long taskId,
                                                  @PathVariable Long tagId) {
        Task task = taskService.getTaskById(taskId);
        Tag tag = tagService.getTagById(tagId);

        task.removeTag(tag);
        Task responseBody = taskService.addTask(task);
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("/tasks/{taskId}/taskgroups/{taskGroupId}")
    public ResponseEntity<Task> assignTaskToTaskGroup(@PathVariable Long taskId,
                                                      @PathVariable Long taskGroupId) {
        Task task = taskService.getTaskById(taskId);
        TaskGroup taskGroup = taskGroupService.getTaskGroupById(taskGroupId);

        task.setTaskGroup(taskGroup);
        Task responseBody = taskService.addTask(task);
        return ResponseEntity.ok(responseBody);
    }
}
