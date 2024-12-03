package com.aliyo.todoapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Document
public class User {
    @Id
    private String id = UUID.randomUUID().toString();  // Generate ID automatically

    @Field
    private String username;

    @Field
    private String email;

    @Field
    private String password;

    @Field
    private Set<Role> roles = new HashSet<>();

    @Field
    private boolean active = true;

    @Field
    private long createdAt = System.currentTimeMillis();
}