package com.affnine.todo.Service;

import com.affnine.todo.Model.Response.GetAllCategoriesResponseDTO;
import com.affnine.todo.Util.ServiceResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskCategoryService {
    public ResponseEntity<ServiceResponse<List<GetAllCategoriesResponseDTO>>> getAllCategories();
}
