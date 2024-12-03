package com.aliyo.todoapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Task {
    @Id
    private String id = UUID.randomUUID().toString();

    @Field
    private String userId;

    @Field
    private String taskListId;  // Reference to TaskList

    @Field
    private String title;

    @Field
    private String description;

    @Field
    private boolean completed = false;

    @Field
    private Priority priority = Priority.MEDIUM;

    @Field
    private Long dueDate;

    @Field
    private Long reminderDate;  // Added reminder functionality

    @Field
    private String labels;  // Comma-separated labels/tags

    @Field
    private long createdAt = System.currentTimeMillis();

    @Field
    private long updatedAt = System.currentTimeMillis();
}