package com.affnine.todo.Controller.Route;

import com.affnine.todo.Model.Response.GetAllStatusResponseDto;
import com.affnine.todo.Util.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "/api/v1/tasks/status")
public interface StatusRoute {
    @GetMapping(value = "/allStatus")
    ResponseEntity<ServiceResponse<List<GetAllStatusResponseDto>>> getStatus();
}
