package com.aliyo.todoapp.controller;

import com.aliyo.todoapp.dto.request.TaskListCreateDto;
import com.aliyo.todoapp.dto.response.TaskListResponseDto;
import com.aliyo.todoapp.service.TaskListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasklists")
@RequiredArgsConstructor
public class TaskListController {
    private final TaskListService taskListService;

    @PostMapping
    public ResponseEntity<TaskListResponseDto> createTaskList(
            @Valid @RequestBody TaskListCreateDto createDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName(); // gets the username (which is our userId in this case)
        return ResponseEntity.ok(taskListService.createTaskList(userId, createDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskListResponseDto> getTaskList(@PathVariable String id) {
        return ResponseEntity.ok(taskListService.getTaskList(id));
    }

    @GetMapping
    public ResponseEntity<List<TaskListResponseDto>> getUserTaskLists() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        return ResponseEntity.ok(taskListService.getUserTaskLists(userId));
    }

    @GetMapping("/default")
    public ResponseEntity<TaskListResponseDto> getDefaultTaskList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        return ResponseEntity.ok(taskListService.getDefaultTaskList(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskListResponseDto> updateTaskList(
            @PathVariable String id,
            @Valid @RequestBody TaskListCreateDto updateDto) {
        return ResponseEntity.ok(taskListService.updateTaskList(id, updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable String id) {
        taskListService.deleteTaskList(id);
        return ResponseEntity.noContent().build();
    }
}