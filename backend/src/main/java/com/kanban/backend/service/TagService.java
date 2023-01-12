package com.kanban.backend.service;

import com.kanban.backend.exception.ResourceNotFoundException;
import com.kanban.backend.model.Tag;
import com.kanban.backend.model.Task;
import com.kanban.backend.repository.TagRepository;
import com.kanban.backend.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final TaskRepository taskRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(Long id) {
        return tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Tag.class.getSimpleName(), id));
    }

    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag deleteTagById(Long id) {
        Tag tagToDelete = tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Tag.class.getSimpleName(), id));
        List<Task> tasksToUpdate = tagToDelete.getTasks();

        // Update tasks that have the tag we want to delete
        for (Task task : tasksToUpdate) {
            Task taskToUpdate = taskRepository.findById(task.getId()).orElseThrow(() -> new ResourceNotFoundException(Task.class.getSimpleName(), id));
            taskToUpdate.removeTag(tagToDelete);
            taskRepository.save(taskToUpdate);
        }

        tagRepository.deleteById(id);
        return tagToDelete;
    }

    public Tag updateTag(Long id, Tag newTag) {
        Tag tagToUpdate = tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Tag.class.getSimpleName(), id));

        if (newTag.getName() != null) {
            tagToUpdate.setName(newTag.getName());
        }

        return tagRepository.save(tagToUpdate);
    }
}
