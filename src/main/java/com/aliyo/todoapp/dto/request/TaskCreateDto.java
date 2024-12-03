package com.aliyo.todoapp.dto.request;


import com.aliyo.todoapp.model.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskCreateDto {
    @NotBlank(message = "Task title is required")
    @Size(min = 1, max = 200, message = "Title must be between 1 and 200 characters")
    private String title;

    private String description;
    private String taskListId;
    private Priority priority;
    private Long dueDate;
    private Long reminderDate;
    private String labels;
}