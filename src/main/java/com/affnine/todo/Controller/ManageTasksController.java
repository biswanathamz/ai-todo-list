package com.affnine.todo.Controller;

import com.affnine.todo.Controller.Route.ManageTasksRoute;
import com.affnine.todo.Model.Request.CreateNewTaskRequest;
import com.affnine.todo.Service.TaskService;
import com.affnine.todo.Util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManageTasksController implements ManageTasksRoute {

    @Autowired
    TaskService taskService;

    @Override
    public ResponseEntity<ServiceResponse<String>> taskCreate(CreateNewTaskRequest request) {
        return taskService.createTask(request);
    }

    @Override
    public ResponseEntity<ServiceResponse<String>> taskUpdate(CreateNewTaskRequest request) {
        return null;
    }
}
