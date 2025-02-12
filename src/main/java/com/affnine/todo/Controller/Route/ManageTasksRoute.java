package com.affnine.todo.Controller.Route;

import com.affnine.todo.Model.Request.CreateNewTaskRequest;
import com.affnine.todo.Model.Request.UpdateTaskRequest;
import com.affnine.todo.Model.Response.GetAllTaskResponseDto;
import com.affnine.todo.Model.Response.GetTaskResponseDto;
import com.affnine.todo.Util.ServiceResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/tasks/manage")
public interface ManageTasksRoute {

    @PostMapping(value = "/create")
    ResponseEntity<ServiceResponse<String>> taskCreate(@RequestBody @Valid CreateNewTaskRequest request);

    @PatchMapping(value = "/{taskId}")
    ResponseEntity<ServiceResponse<String>> taskUpdate(@PathVariable Long taskId, @RequestBody @Valid UpdateTaskRequest request);

    @GetMapping(value = "/showTask/{taskId}")
    ResponseEntity<ServiceResponse<GetTaskResponseDto>> showTask(@PathVariable Long taskId);

    @GetMapping(value = "/showTask/allTasks")
    ResponseEntity<ServiceResponse<List<GetAllTaskResponseDto>>> showAllTasks();
}
