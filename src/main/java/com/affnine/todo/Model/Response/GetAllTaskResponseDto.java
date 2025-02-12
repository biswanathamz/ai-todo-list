package com.affnine.todo.Model.Response;

import lombok.Data;

import java.util.Map;

@Data
public class GetAllTaskResponseDto {
    private long id;
    private String taskName;
    private String status;
}
