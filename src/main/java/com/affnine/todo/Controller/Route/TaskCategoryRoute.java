package com.affnine.todo.Controller.Route;

import com.affnine.todo.Model.Response.GetAllCategoriesResponseDTO;
import com.affnine.todo.Util.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "/api/v1/categories")
public interface TaskCategoryRoute {
    @GetMapping(value = "allCategories")
    public ResponseEntity<ServiceResponse<List<GetAllCategoriesResponseDTO>>> getAllCategories();
}
