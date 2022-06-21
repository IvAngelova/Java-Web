package com.example.hateoas.model.mapping;

import com.example.hateoas.model.dto.StudentDto;
import com.example.hateoas.model.entity.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDto mapEntityToDTO(StudentEntity student);
}
