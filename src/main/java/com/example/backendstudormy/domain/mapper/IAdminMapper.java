package com.example.backendstudormy.domain.mapper;
import com.example.backendstudormy.domain.dto.admin.getAdmin.GetAdminResponseDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.entities.Admin;
import com.example.backendstudormy.domain.entities.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IAdminMapper {
    GetAdminResponseDTO adminToGetAdminResponseDto(Admin admin);


}
