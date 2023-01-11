package com.kanban.backend.service;

import com.kanban.backend.exception.ResourceNotFoundException;
import com.kanban.backend.model.Tag;
import com.kanban.backend.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(Long id) {
        return tagRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(Tag.class.getSimpleName(), id));
    }

    public Tag addTag(Tag tag) {return tagRepository.save(tag); }

    public Tag deleteTagById(Long id) {
        Tag tagToDelete = tagRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(Tag.class.getSimpleName(), id));
        tagToDelete.clearTasks();
        tagRepository.save(tagToDelete);
        tagRepository.delete(tagToDelete);
        return tagToDelete;
    }
}
