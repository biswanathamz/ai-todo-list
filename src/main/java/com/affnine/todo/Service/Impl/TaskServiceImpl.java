package com.affnine.todo.Service.Impl;

import com.affnine.todo.Constant.AppConstants;
import com.affnine.todo.Enum.TaskStatus;
import com.affnine.todo.Model.Request.CreateNewTaskRequest;
import com.affnine.todo.Model.Request.UpdateTaskRequest;
import com.affnine.todo.Model.Response.GetAllStatusResponseDto;
import com.affnine.todo.Model.Response.GetTaskResponseDto;
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
import jakarta.validation.constraints.Null;
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

    @Override
    public ResponseEntity<ServiceResponse<String>> updateTask(Long taskId, UpdateTaskRequest request) {
        try{
            // Fetch the UserId
            long userId = 1;

            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + taskId));

            if(!task.getUser().getId().equals(userId)){
                return ResponseUtils.badRequest(AppConstants.UNAUTHORIZED_UPDATED_TASK_MESSAGE, null);
            }

            if (request.getTaskName() != null) {
                task.setName(request.getTaskName());
            }
            if (request.getTaskDescription() != null) {
                task.setDescription(request.getTaskDescription());
            }
            if (request.getDueTimestamp() != null) {
                task.setDueTimestamp(CommonUtils.epochTimeToString(request.getDueTimestamp()));
            }
            if (request.getCategoryId() != null) {
                TaskCategory category = taskCategoryRepository.findById(Long.valueOf(request.getCategoryId()))
                        .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + request.getCategoryId()));
                task.setCategory(category);
            }
            task.setUpdatedAt(LocalDateTime.now());
            taskRepository.save(task);
            return ResponseUtils.successResponse(AppConstants.DATA_UPDATED_MESSAGE_SUCCESS,null);
        }catch (EntityNotFoundException e){
            return ResponseUtils.internalServerError(AppConstants.INTERNAL_SERVER_ERROR_MESSAGE, e.getMessage());
        } catch (Exception e) {
            return ResponseUtils.internalServerError(AppConstants.INTERNAL_SERVER_ERROR_MESSAGE, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ServiceResponse<GetTaskResponseDto>> showTask(Long taskId) {
        try {
            // Fetch the UserId
            long userId = 1;

            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + taskId));

            if(!task.getUser().getId().equals(userId)){
                return ResponseUtils.badRequest(AppConstants.UNAUTHORIZED_GET_TASK_DETAILS_MESSAGE, null);
            }

            GetTaskResponseDto getTaskResponseDto = new GetTaskResponseDto();
            getTaskResponseDto.setTaskName(task.getName());
            getTaskResponseDto.setTaskDescription(task.getDescription());
            getTaskResponseDto.setCategoryId(Math.toIntExact(task.getCategory().getId()));
            getTaskResponseDto.setDueTimestamp(String.valueOf(task.getDueTimestamp()));
            getTaskResponseDto.setStatus(task.getStatus());
            getTaskResponseDto.setMetaData(null);
            getTaskResponseDto.setCompletionTimeStamp(String.valueOf(task.getCompletionTimestamp()));
            return ResponseUtils.successResponse(AppConstants.GET_GENERIC_RESPONSE_MESSAGE_SUCCESS,getTaskResponseDto);
        }catch (EntityNotFoundException e){
            return ResponseUtils.internalServerError(AppConstants.INTERNAL_SERVER_ERROR_MESSAGE, null);
        } catch (Exception e) {
            return ResponseUtils.internalServerError(AppConstants.INTERNAL_SERVER_ERROR_MESSAGE, null);
        }
    }
}
