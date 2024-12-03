package com.aliyo.todoapp.dto.response;

import com.aliyo.todoapp.model.Priority;
import lombok.Data;

@Data
public class TaskResponseDto {
    private String id;
    private String taskListId;
    private String title;
    private String description;
    private boolean completed;
    private Priority priority;
    private Long dueDate;
    private Long reminderDate;
    private String labels;
    private long createdAt;
    private long updatedAt;
}
