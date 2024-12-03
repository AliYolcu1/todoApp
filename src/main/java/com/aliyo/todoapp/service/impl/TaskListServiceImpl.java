package com.aliyo.todoapp.service.impl;

import com.aliyo.todoapp.dto.request.TaskListCreateDto;
import com.aliyo.todoapp.dto.response.TaskListResponseDto;
import com.aliyo.todoapp.mapper.TaskListMapper;
import com.aliyo.todoapp.model.TaskList;
import com.aliyo.todoapp.repository.TaskListRepository;
import com.aliyo.todoapp.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;
    private final TaskListMapper taskListMapper;

    @Override
    public TaskListResponseDto createTaskList(String userId, TaskListCreateDto createDto) {
        TaskList taskList = taskListMapper.toEntity(createDto);
        taskList.setUserId(userId);

        if (createDto.isDefault()) {
            taskListRepository.findByUserIdAndIsDefaultTrue(userId)
                    .ifPresent(existingDefault -> {
                        existingDefault.setDefault(false);
                        taskListRepository.save(existingDefault);
                    });
        }

        return taskListMapper.toDto(taskListRepository.save(taskList));
    }

    @Override
    public TaskListResponseDto getTaskList(String id) {
        return taskListMapper.toDto(taskListRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("TaskList not found")));
    }

    @Override
    public List<TaskListResponseDto> getUserTaskLists(String userId) {
        return taskListRepository.findByUserId(userId).stream()
                .map(taskListMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskListResponseDto updateTaskList(String id, TaskListCreateDto updateDto) {
        TaskList taskList = taskListRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("TaskList not found"));

        TaskList updatedTaskList = taskListMapper.toEntity(updateDto);
        updatedTaskList.setId(id);
        updatedTaskList.setUserId(taskList.getUserId());

        if (updateDto.isDefault() && !taskList.isDefault()) {
            taskListRepository.findByUserIdAndIsDefaultTrue(taskList.getUserId())
                    .ifPresent(existingDefault -> {
                        existingDefault.setDefault(false);
                        taskListRepository.save(existingDefault);
                    });
        }

        return taskListMapper.toDto(taskListRepository.save(updatedTaskList));
    }

    @Override
    public void deleteTaskList(String id) {
        if (!taskListRepository.existsById(id)) {
            throw new NoSuchElementException("TaskList not found");
        }
        taskListRepository.deleteById(id);
    }

    @Override
    public TaskListResponseDto getDefaultTaskList(String userId) {
        return taskListMapper.toDto(taskListRepository.findByUserIdAndIsDefaultTrue(userId)
                .orElseThrow(() -> new NoSuchElementException("Default TaskList not found")));
    }
}