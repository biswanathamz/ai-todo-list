package com.affnine.todo.Service.Impl;

import com.affnine.todo.Constant.AppConstants;
import com.affnine.todo.Enum.TaskStatus;
import com.affnine.todo.Model.Request.CreateNewTaskRequest;
import com.affnine.todo.Model.Response.GetAllStatusResponseDto;
import com.affnine.todo.Model.Task;
import com.affnine.todo.Model.TaskCategory;
import com.affnine.todo.Model.User;
import com.affnine.todo.Repository.TaskCategoryRepository;
import com.affnine.todo.Repository.TaskRepository;
import com.affnine.todo.Repository.UserRepository;
import com.affnine.todo.Service.TaskService;
import com.affnine.todo.Util.CommonUtils;
import com.affnine.todo.Util.ResponseUtils;
import com.affnine.todo.Util.ServiceResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public ResponseEntity<ServiceResponse<List<GetAllStatusResponseDto>>> getStatus() {
        try {
            List<GetAllStatusResponseDto> statusList = new ArrayList<>();
            for (TaskStatus taskStatus : TaskStatus.values()) {
                GetAllStatusResponseDto dto = new GetAllStatusResponseDto();
                dto.setStatus(taskStatus.name());
                statusList.add(dto);
            }
            return ResponseUtils.successResponse(AppConstants.GET_GENERIC_RESPONSE_MESSAGE_SUCCESS,statusList);
        }catch (Exception e){
            return ResponseUtils.internalServerError(AppConstants.INTERNAL_SERVER_ERROR_MESSAGE,null);
        }
    }

    @Override
    public ResponseEntity<ServiceResponse<String>> createTask(CreateNewTaskRequest request) {
        try {
            // Fetch the UserId
            long userId = 1;

            // Validate User existence
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));

            // Validate Category existence
            TaskCategory taskCategory = taskCategoryRepository.findById((long) request.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category with ID " + request.getCategoryId() + " not found"));

            // Create new Task object
            Task task = new Task();
            task.setName(request.getTaskName());
            task.setDescription(request.getTaskDescription());
            task.setDueTimestamp(CommonUtils.epochTimeToString(request.getDueTimestamp()));
            task.setContainMetaData(false);
            task.setStatus(String.valueOf(TaskStatus.PENDING));
            task.setCreatedAt(LocalDateTime.now());
            task.setUpdatedAt(LocalDateTime.now());
            task.setUser(user);
            task.setCategory(taskCategory);

            taskRepository.save(task);

            return ResponseUtils.successResponse(AppConstants.DATA_CREATED_MESSAGE_SUCCESS,null);


        }catch (EntityNotFoundException e){
            return ResponseUtils.internalServerError(AppConstants.INTERNAL_SERVER_ERROR_MESSAGE, e.getMessage());
        }
        catch (Exception e) {
            return ResponseUtils.internalServerError(AppConstants.INTERNAL_SERVER_ERROR_MESSAGE, e.getMessage());
        }
    }
}
