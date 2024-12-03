package com.aliyo.todoapp.service.impl;

import com.aliyo.todoapp.dto.request.TaskCreateDto;
import com.aliyo.todoapp.dto.request.TaskUpdateDto;
import com.aliyo.todoapp.dto.response.TaskResponseDto;
import com.aliyo.todoapp.mapper.TaskMapper;
import com.aliyo.todoapp.model.Task;
import com.aliyo.todoapp.repository.TaskRepository;
import com.aliyo.todoapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponseDto createTask(String userId, TaskCreateDto createDto) {
        Task task = taskMapper.toEntity(createDto);
        task.setUserId(userId);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskResponseDto getTask(String id) {
        return taskMapper.toDto(taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task not found")));
    }

    @Override
    public List<TaskResponseDto> getAllTasksByUserId(String userId) {
        return taskRepository.findByUserId(userId).stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponseDto> getTasksByListId(String taskListId) {
        return taskRepository.findByTaskListId(taskListId).stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponseDto> getTasksByUserIdAndCompleted(String userId, boolean completed) {
        return taskRepository.findByUserIdAndCompleted(userId, completed).stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDto updateTask(String id, TaskUpdateDto updateDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));

        taskMapper.updateEntity(task, updateDto);
        task.setUpdatedAt(System.currentTimeMillis());
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public void deleteTask(String id) {
        if (!taskRepository.existsById(id)) {
            throw new NoSuchElementException("Task not found");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public long countTasksByListId(String taskListId) {
        return taskRepository.countByTaskListId(taskListId);
    }

    @Override
    public long countCompletedTasks(String userId) {
        return taskRepository.countByUserIdAndCompleted(userId, true);
    }
}
