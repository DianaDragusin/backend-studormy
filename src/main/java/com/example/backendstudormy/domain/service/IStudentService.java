package com.example.backendstudormy.domain.service;

import com.example.backendstudormy.domain.dto.clustering.ClusteringRequestDTO;
import com.example.backendstudormy.domain.dto.clustering.ClusteringResponseDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentOceanRequestDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentOceanResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentResponseDTO;
import com.example.backendstudormy.domain.exceptions.CustomException;

import java.util.List;

public interface IStudentService {
    List<AddStudentResponseDTO> uploadStudents(Integer adminId, List<AddStudentRequestDTO> addStudentRequestDTOList);
    AddStudentResponseDTO addStudent(Integer adminId, AddStudentRequestDTO addStudentRequestDto) throws CustomException;
    UpdateStudentResponseDTO updateStudent(Integer studentId, UpdateStudentRequestDTO updateStudentRequestDTO);
    UpdateStudentOceanResponseDTO updateStudentOcean(Integer studentId, UpdateStudentOceanRequestDTO updateStudentOceanRequestDTO);
   void updateStudentCluster(Integer studentId,  ClusteringResponseDTO clusteringResponseDTO);

    GetStudentResponseDTO getStudentById(Integer studentId) throws CustomException;
    void deleteStudentById(Integer studentId);
}
