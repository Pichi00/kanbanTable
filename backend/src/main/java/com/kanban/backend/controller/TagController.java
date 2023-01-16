package com.kanban.backend.controller;

import com.kanban.backend.model.Table;
import com.kanban.backend.model.Tag;
import com.kanban.backend.service.TableService;
import com.kanban.backend.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final TableService tableService;

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> responseBody = tagService.getAllTags();
        return ResponseEntity.ok().body(responseBody);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
        Tag responseBody = tagService.getTagById(id);
        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping("/tags")
    public ResponseEntity<Tag> addTag(@RequestBody Tag tag,
                                      @RequestParam(defaultValue = "0") Long table) {
        if (table != 0) {
            Table tableToAssign = tableService.getTableById(table);
            tag.setTable(tableToAssign);
        }
        Tag responseBody = tagService.addTag(tag);
        return ResponseEntity.ok().body(responseBody);
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<Tag> deleteTagById(@PathVariable Long id) {
        Tag responseBody = tagService.deleteTagById(id);
        return ResponseEntity.ok().body(responseBody);
    }

    @PutMapping("/tags/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id,
                                         @RequestBody Tag newTag) {
        Tag responseBody = tagService.updateTag(id, newTag);

        return ResponseEntity.ok().body(responseBody);
    }
}
