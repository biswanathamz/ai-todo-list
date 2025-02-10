package com.affnine.todo.Controller;

import com.affnine.todo.Controller.Route.StatusRoute;
import com.affnine.todo.Model.Response.GetAllStatusResponseDto;
import com.affnine.todo.Service.TaskService;
import com.affnine.todo.Util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatusController implements StatusRoute {

    @Autowired
    TaskService taskService;

    @Override
    public ResponseEntity<ServiceResponse<List<GetAllStatusResponseDto>>> getStatus() {
        return taskService.getStatus();
    }
}
