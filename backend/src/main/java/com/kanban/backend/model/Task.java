package com.kanban.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity(name = "T_TASK")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;

    // Relations
    @ManyToOne
    @JoinColumn(name = "taskgroup_id")
    @NonNull
    @JsonIgnore
    private TaskGroup taskGroup;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "task_tag",
            joinColumns = @JoinColumn(
                    name = "tag_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "task_id",
                    referencedColumnName = "id"
            )
    )
    @NonNull
    private List<Tag> tags;

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void clearTags() {
        tags.clear();
    }

    public void removeTag(Tag tagToDelete) {
        tags.remove(tagToDelete);
    }
}
