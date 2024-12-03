package com.aliyo.todoapp.dto.response;

import lombok.Data;

@Data
public class TaskListResponseDto {
    private String id;
    private String name;
    private String description;
    private String color;
    private boolean isDefault;
    private long createdAt;
    private long updatedAt;
}
