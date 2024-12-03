package com.aliyo.todoapp.dto.request;

import com.aliyo.todoapp.model.Priority;
import lombok.Data;

@Data
public class TaskUpdateDto {
    private String title;
    private String description;
    private String taskListId;
    private Boolean completed;
    private Priority priority;
    private Long dueDate;
    private Long reminderDate;
    private String labels;
}
