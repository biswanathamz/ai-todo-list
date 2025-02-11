package com.affnine.todo.Model.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewTaskRequest {

    @Size(min = 3, max = 255, message = "Task name should be between 3 to 255 characters")
    @NotEmpty(message = "taskName can't be null")
    private String taskName;

    @Size(min = 3, message = "Task description should have at least 3 characters")
    @NotEmpty(message = "taskDescription can't be null")
    private String taskDescription;

    @NotNull(message = "categoryId can't be null")
    private int categoryId;

    @NotEmpty(message = "dueTimestamp can't be null")
    private String dueTimestamp;

}
