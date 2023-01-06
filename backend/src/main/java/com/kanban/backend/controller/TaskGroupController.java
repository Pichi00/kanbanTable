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

    @GetMapping("/taskgroups/{id}")
    public TaskGroup getTaskGroupById(@PathVariable Long id) {
        return taskGroupService.getTaskGroupById(id);
    }

    @PostMapping("/taskgroups")
    public TaskGroup addTaskGroup(@RequestBody TaskGroup taskGroup) {
        return taskGroupService.addTaskGroup(taskGroup);
    }

    @DeleteMapping("/taskgroups/{id}")
    public void deleteTaskGroupById(@PathVariable Long id) {
        taskGroupService.deleteTaskGroupById(id);
    }
}
