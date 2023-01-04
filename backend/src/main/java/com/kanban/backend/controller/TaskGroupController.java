package com.kanban.backend.controller;

import com.kanban.backend.model.TaskGroup;
import com.kanban.backend.service.TaskGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskGroupController {
    private final TaskGroupService taskGroupService;

    @GetMapping("/taskgroups")
    public List<TaskGroup> getAllTaskGroups() {
        return taskGroupService.getAllTaskGroups();
    }

    @GetMapping("/tasksgroup/{id}")
    public TaskGroup getTaskGroupById(@PathVariable Long id) {
        return taskGroupService.getTaskGroupById(id);
    }

    @PostMapping("/tasksgroup")
    public void addTaskGroup(@RequestBody TaskGroup taskGroup) {
        taskGroupService.addTaskGroup(taskGroup);
    }

    @DeleteMapping("/tasksgroup/{id}")
    public void deleteTaskGroupById(@PathVariable Long id) {
        taskGroupService.deleteTaskGroupById(id);
    }
}
