package com.affnine.todo.Controller;

import com.affnine.todo.Model.Response.GetAllCategoriesResponseDTO;
import com.affnine.todo.Service.TaskCategoryService;
import com.affnine.todo.Util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/categories")
public class TaskCategoryController {

    @Autowired
    TaskCategoryService taskCategoryService;

    @GetMapping(value = "allCategories")
    public ResponseEntity<ServiceResponse<List<GetAllCategoriesResponseDTO>>> getAllCategories() {
        return taskCategoryService.getAllCategories();
    }
}
