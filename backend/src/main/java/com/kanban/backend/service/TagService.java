package com.kanban.backend.service;

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
        return tagRepository.findById(id).orElse(null);
    }

    public void addTag(Tag tag) {
        tagRepository.save(tag);
    }

    public void deleteTagById(Long id) {
        tagRepository.deleteById(id);
    }
}
