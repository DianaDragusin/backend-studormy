package com.example.backendstudormy.domain.service;

import com.example.backendstudormy.domain.dto.Membership.GetStudentMembershipValues;
import com.example.backendstudormy.domain.dto.clustering.ClusteringRequestDTO;
import com.example.backendstudormy.domain.dto.clustering.ClusteringResponseDTO;
import com.example.backendstudormy.domain.dto.lessInfoStudent.LessInfoStudent;
import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomResponseDTO;
import com.example.backendstudormy.domain.dto.room.getRoom.GetRoomResponseDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentOceanRequestDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentOceanResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentResponseDTO;
import com.example.backendstudormy.domain.entities.Room;
import com.example.backendstudormy.domain.exceptions.CustomException;

import java.util.List;

public interface IStudentService {
    List<AddStudentResponseDTO> uploadStudents(Integer adminId, List<AddStudentRequestDTO> addStudentRequestDTOList);
    AddStudentResponseDTO addStudent(Integer adminId, AddStudentRequestDTO addStudentRequestDto) throws CustomException;
    UpdateStudentResponseDTO updateStudent(Integer studentId, UpdateStudentRequestDTO updateStudentRequestDTO);
    UpdateStudentOceanResponseDTO updateStudentOcean(Integer studentId, UpdateStudentOceanRequestDTO updateStudentOceanRequestDTO);
   void updateStudentCluster(Integer studentId,  ClusteringResponseDTO clusteringResponseDTO);


    GetStudentResponseDTO getStudentById(Integer studentId) throws CustomException;
    LessInfoStudent getStudentLessInfoStudentById(Integer studentId) throws CustomException;
   ClusteringRequestDTO getScoresStudentById(Integer studentId) throws CustomException;
    Boolean getStudentHasRoom(Integer studentId) throws CustomException;
    List<GetStudentResponseDTO> getStudents(Integer studentId) throws CustomException;
    List<Integer> getRoommatesIds(Integer studentId) throws CustomException;
    List<LessInfoStudent> getRoommates(Integer studentId) throws CustomException;
    List<LessInfoStudent> getRoommatesOnly(Integer studentId) throws CustomException;
    AddRoomResponseDTO getRoomOfStudent(Integer studentId) throws  CustomException;
    List<GetStudentResponseDTO> getRoommatesOfStudent(Integer studentId) throws CustomException;

    List<GetStudentResponseDTO> getStudentsByCluster(Integer id) throws CustomException;
    void deleteStudentById(Integer studentId);
}
