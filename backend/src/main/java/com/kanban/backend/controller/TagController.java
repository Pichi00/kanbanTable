package com.kanban.backend.controller;

import com.kanban.backend.model.Tag;
import com.kanban.backend.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping("/tags")
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/tags/{id}")
    public Tag getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }

    @PostMapping("/tags")
    public Tag addTag(@RequestBody Tag tag) { return tagService.addTag(tag);
    }

    @DeleteMapping("/tags/{id}")
    public void deleteTagById(@PathVariable Long id) {
        tagService.deleteTagById(id);
    }
}