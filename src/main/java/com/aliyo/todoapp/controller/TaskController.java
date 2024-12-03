package com.aliyo.todoapp.controller;


import com.aliyo.todoapp.dto.request.TaskCreateDto;
import com.aliyo.todoapp.dto.request.TaskUpdateDto;
import com.aliyo.todoapp.dto.response.TaskResponseDto;
import com.aliyo.todoapp.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody TaskCreateDto createDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        return ResponseEntity.ok(taskService.createTask(userId, createDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable String id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getUserTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        return ResponseEntity.ok(taskService.getAllTasksByUserId(userId));
    }

    @GetMapping("/list/{taskListId}")
    public ResponseEntity<List<TaskResponseDto>> getTasksByList(@PathVariable String taskListId) {
        return ResponseEntity.ok(taskService.getTasksByListId(taskListId));
    }

    @GetMapping("/completed")
    public ResponseEntity<List<TaskResponseDto>> getCompletedTasks(
            @RequestParam boolean completed) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        return ResponseEntity.ok(taskService.getTasksByUserIdAndCompleted(userId, completed));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(
            @PathVariable String id,
            @Valid @RequestBody TaskUpdateDto updateDto) {
        return ResponseEntity.ok(taskService.updateTask(id, updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list/{taskListId}/count")
    public ResponseEntity<Long> countTasksInList(@PathVariable String taskListId) {
        return ResponseEntity.ok(taskService.countTasksByListId(taskListId));
    }

    @GetMapping("/completed/count")
    public ResponseEntity<Long> countCompletedTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        return ResponseEntity.ok(taskService.countCompletedTasks(userId));
    }
}