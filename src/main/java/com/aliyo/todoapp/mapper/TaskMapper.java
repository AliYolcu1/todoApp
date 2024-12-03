package com.aliyo.todoapp.mapper;

import com.aliyo.todoapp.dto.request.TaskCreateDto;
import com.aliyo.todoapp.dto.request.TaskUpdateDto;
import com.aliyo.todoapp.dto.response.TaskResponseDto;
import com.aliyo.todoapp.model.Task;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(TaskCreateDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Task task, TaskUpdateDto dto);

    TaskResponseDto toDto(Task task);
}
