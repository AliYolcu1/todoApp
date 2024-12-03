package com.aliyo.todoapp.dto.response;

import com.aliyo.todoapp.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponseDto {
    private String id;
    private String username;
    private String email;
    private Set<Role> roles;
    private boolean active;
    private long createdAt;
}