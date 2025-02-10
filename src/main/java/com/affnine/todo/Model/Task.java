package com.affnine.todo.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tasks", schema = "public")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_tasks_category"))
    private TaskCategory category;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_tasks_user"))
    private User user;

    @Column(name = "due_timestamp", nullable = false)
    private LocalDateTime dueTimestamp;

    @Column(name = "is_contain_meta_data", nullable = false)
    private Boolean containMetaData = false;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(name = "completion_timestamp")
    private LocalDateTime completionTimestamp;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setLastUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
