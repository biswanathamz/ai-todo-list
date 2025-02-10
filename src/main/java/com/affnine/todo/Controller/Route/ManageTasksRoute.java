package com.affnine.todo.Controller.Route;

import com.affnine.todo.Model.Request.CreateNewTaskRequest;
import com.affnine.todo.Util.ServiceResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/v1/tasks/manage")
public interface ManageTasksRoute {

    @PostMapping(value = "/create")
    ResponseEntity<ServiceResponse<String>> taskCreate(@RequestBody @Valid CreateNewTaskRequest request);

    @PostMapping(value = "/update")
    ResponseEntity<ServiceResponse<String>> taskUpdate(@RequestBody @Valid CreateNewTaskRequest request);
}
