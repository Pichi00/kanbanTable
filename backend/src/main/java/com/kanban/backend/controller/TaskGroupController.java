package com.kanban.backend.controller;

import com.kanban.backend.model.Table;
import com.kanban.backend.model.TaskGroup;
import com.kanban.backend.service.TableService;
import com.kanban.backend.service.TaskGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskGroupController {
    private final TaskGroupService taskGroupService;
    private final TableService tableService;

    @GetMapping("/taskgroups")
    public ResponseEntity<List<TaskGroup>> getAllTaskGroups() {
        List<TaskGroup> responseBody = taskGroupService.getAllTaskGroups();
        return ResponseEntity.ok().body(responseBody);
    }

    @GetMapping("/taskgroups/{id}")
    public ResponseEntity<TaskGroup> getTaskGroupById(@PathVariable Long id) {
        TaskGroup responseBody = taskGroupService.getTaskGroupById(id);
        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping("/taskgroups")
    public ResponseEntity<TaskGroup> addTaskGroup(@RequestBody TaskGroup taskGroup,
                                                  @RequestParam(defaultValue = "0") Long table) {

        if (table != 0) {
            Table tableToAssign = tableService.getTableById(table);
            taskGroup.setTable(tableToAssign);
        }
        TaskGroup responseBody = taskGroupService.addTaskGroup(taskGroup);
        return ResponseEntity.ok().body(responseBody);
    }

    @DeleteMapping("/taskgroups/{id}")
    public ResponseEntity<TaskGroup> deleteTaskGroupById(@PathVariable Long id) {
        TaskGroup responseBody = taskGroupService.deleteTaskGroupById(id);
        return ResponseEntity.ok().body(responseBody);
    }

    @PutMapping("taskgroups/{taskGroupId}/tables/{tableId}")
    public ResponseEntity<TaskGroup> assignTableToTaskGroup(@PathVariable Long taskGroupId, @PathVariable Long tableId) {
        TaskGroup taskGroup = taskGroupService.getTaskGroupById(taskGroupId);
        Table table = tableService.getTableById(tableId);

        taskGroup.setTable(table);
        TaskGroup responseBody = taskGroupService.addTaskGroup(taskGroup);
        return ResponseEntity.ok().body(responseBody);
    }
}
