package com.affnine.todo.Model.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewTaskRequest {

    @NotEmpty(message = "taskName can't be null")
    private String taskName;

    @NotEmpty(message = "taskDescription can't be null")
    private String taskDescription;

    @NotNull(message = "categoryId can't be null")
    private int categoryId;

    @NotEmpty(message = "dueTimestamp can't be null")
    private String dueTimestamp;

}
