package com.aliyo.todoapp.mapper;

import com.aliyo.todoapp.dto.request.UserRegistrationDto;
import com.aliyo.todoapp.dto.response.UserResponseDto;
import com.aliyo.todoapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRegistrationDto dto);

    UserResponseDto toDto(User user);
}

