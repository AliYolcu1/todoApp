package com.aliyo.todoapp.service;

import com.aliyo.todoapp.dto.request.UserRegistrationDto;
import com.aliyo.todoapp.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto registerUser(UserRegistrationDto registrationDto);

    UserResponseDto getUserById(String id);

    UserResponseDto getUserByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void deleteUser(String id);
}
