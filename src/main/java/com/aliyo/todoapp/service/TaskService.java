package com.aliyo.todoapp.service;

import com.aliyo.todoapp.dto.request.TaskCreateDto;
import com.aliyo.todoapp.dto.request.TaskUpdateDto;
import com.aliyo.todoapp.dto.response.TaskResponseDto;

import java.util.List;

public interface TaskService {
    TaskResponseDto createTask(String userId, TaskCreateDto createDto);

    TaskResponseDto getTask(String id);

    List<TaskResponseDto> getAllTasksByUserId(String userId);

    List<TaskResponseDto> getTasksByListId(String taskListId);

    List<TaskResponseDto> getTasksByUserIdAndCompleted(String userId, boolean completed);

    TaskResponseDto updateTask(String id, TaskUpdateDto updateDto);

    void deleteTask(String id);

    long countTasksByListId(String taskListId);

    long countCompletedTasks(String userId);
}
