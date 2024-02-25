package com.example.backendstudormy.domain.mapper;

import com.example.backendstudormy.domain.dto.login.LoginAdminResponseDTO;
import com.example.backendstudormy.domain.dto.login.LoginStudentResponseDTO;
import com.example.backendstudormy.domain.entities.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ILoginStudentMapper {
    LoginStudentResponseDTO studentToLoginStudentResponseDto(Student student);

}
