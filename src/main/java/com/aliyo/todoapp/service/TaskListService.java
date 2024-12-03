package com.aliyo.todoapp.service;


import com.aliyo.todoapp.dto.request.TaskListCreateDto;
import com.aliyo.todoapp.dto.response.TaskListResponseDto;

import java.util.List;

public interface TaskListService {
    TaskListResponseDto createTaskList(String userId, TaskListCreateDto createDto);

    TaskListResponseDto getTaskList(String id);

    List<TaskListResponseDto> getUserTaskLists(String userId);

    TaskListResponseDto updateTaskList(String id, TaskListCreateDto updateDto);

    void deleteTaskList(String id);

    TaskListResponseDto getDefaultTaskList(String userId);
}
