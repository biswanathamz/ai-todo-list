package com.affnine.todo.Service;

import com.affnine.todo.Model.Request.CreateNewTaskRequest;
import com.affnine.todo.Model.Response.GetAllStatusResponseDto;
import com.affnine.todo.Util.ServiceResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
    ResponseEntity<ServiceResponse<List<GetAllStatusResponseDto>>> getStatus();
    ResponseEntity<ServiceResponse<String>> createTask(CreateNewTaskRequest request);
}
