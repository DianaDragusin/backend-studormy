package com.example.backendstudormy.domain.service;

import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentResponseDTO;
import com.example.backendstudormy.domain.exceptions.CustomException;

import java.util.List;

public interface IStudentService {
    List<AddStudentResponseDTO> uploadStudents(Integer adminId, List<AddStudentRequestDTO> addStudentRequestDTOList);
    AddStudentResponseDTO addStudent(Integer adminId, AddStudentRequestDTO addStudentRequestDto) throws CustomException;
    UpdateStudentResponseDTO updateAsset(Integer studentId, UpdateStudentRequestDTO updateStudentRequestDTO);
    GetStudentResponseDTO getStudentById(Integer studentId) throws CustomException;
    void deleteAssetById(Integer studentId);
}
