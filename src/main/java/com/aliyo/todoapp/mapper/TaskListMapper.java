package com.aliyo.todoapp.mapper;


import com.aliyo.todoapp.dto.request.TaskListCreateDto;
import com.aliyo.todoapp.dto.response.TaskListResponseDto;
import com.aliyo.todoapp.model.TaskList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskListMapper {

    TaskList toEntity(TaskListCreateDto dto);

    TaskListResponseDto toDto(TaskList taskList);
}