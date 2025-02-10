package com.affnine.todo.Model.Response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Map;

@Data
public class GetTaskResponseDto {
    private String taskName;

    private String taskDescription;

    private Integer categoryId;

    private String dueTimestamp;

    private String status;

    private Map<String,String> metaData;

    private String completionTimeStamp;
}
