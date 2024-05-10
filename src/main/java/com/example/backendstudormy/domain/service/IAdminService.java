package com.example.backendstudormy.domain.service;

import com.example.backendstudormy.domain.dto.admin.getAdmin.GetAdminResponseDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.exceptions.CustomException;

import java.util.List;

public interface IAdminService {
    GetAdminResponseDTO getAdminById(Integer adminId) throws CustomException;
    GetAdminResponseDTO getAdminByDormitoryId(Integer dormitoryId) throws CustomException;


}
