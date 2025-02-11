package com.affnine.todo.Service.Impl;

import com.affnine.todo.Constant.AppConstants;
import com.affnine.todo.Model.Response.GetAllCategoriesResponseDTO;
import com.affnine.todo.Model.TaskCategory;
import com.affnine.todo.Repository.TaskCategoryRepository;
import com.affnine.todo.Service.TaskCategoryService;
import com.affnine.todo.Util.ResponseUtils;
import com.affnine.todo.Util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskCategoryServiceImpl implements TaskCategoryService {

    @Autowired
    TaskCategoryRepository taskCategoryRepository;

    @Override
    public ResponseEntity<ServiceResponse<List<GetAllCategoriesResponseDTO>>> getAllCategories() {
        List<GetAllCategoriesResponseDTO> allCategories = new ArrayList<>();
        List<TaskCategory> taskCategories = taskCategoryRepository.findAll();
        try{
            for (TaskCategory data : taskCategories){
                GetAllCategoriesResponseDTO category = new GetAllCategoriesResponseDTO();
                category.setId(data.getId());
                category.setName(data.getName());
                category.setDescription(data.getDescription());
                allCategories.add(category);
            }
            return ResponseUtils.successResponse(AppConstants.GET_GENERIC_RESPONSE_MESSAGE_SUCCESS,allCategories);
        }catch (Exception ex){
            return ResponseUtils.internalServerError(AppConstants.INTERNAL_SERVER_ERROR_MESSAGE,null);
        }
    }
}
