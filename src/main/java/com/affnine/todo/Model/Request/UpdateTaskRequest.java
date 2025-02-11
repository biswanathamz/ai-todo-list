package com.affnine.todo.Model.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTaskRequest {
    @Size(min = 3, max = 255, message = "Task name should be between 3 to 255 characters")
    private String taskName;

    @Size(min = 3, message = "Task description should have at least 3 characters")
    private String taskDescription;

    private Integer categoryId;

    private String dueTimestamp;
}
