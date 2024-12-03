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
public class TaskList {
    @Id
    private String id = UUID.randomUUID().toString();

    @Field
    private String userId;

    @Field
    private String name;

    @Field
    private String description;

    @Field
    private String color;  // For UI customization

    @Field
    private boolean isDefault = false;  // Default list for new tasks

    @Field
    private long createdAt = System.currentTimeMillis();

    @Field
    private long updatedAt = System.currentTimeMillis();
}
