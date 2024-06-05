package com.example.backendstudormy.domain.service.remainingStudents;

import com.example.backendstudormy.domain.dto.clustering.BigFiveResponses;
import com.example.backendstudormy.domain.dto.clustering.ClusteringRequestDTO;
import com.example.backendstudormy.domain.dto.clustering.ClusteringResponseDTO;
import com.example.backendstudormy.domain.dto.group.GroupResponseDTO;
import com.example.backendstudormy.domain.dto.room.getRoom.GetRoomResponseDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.entities.Room;
import com.example.backendstudormy.domain.entities.Student;
import com.example.backendstudormy.domain.exceptions.CustomException;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Map;

public interface IAssignRemainingStudents {
    List<GroupResponseDTO> moveRemainingStudentsToVacantRooms(Integer dormitoryId, Map<Integer,List<Integer>> rankedClustersOfStudents) throws CustomException;
    List<BigFiveResponses> getStudentsWithNoRoom(Integer dormitoryId) throws CustomException;
    BigFiveResponses getFiveScores(Integer studentId) throws CustomException;
    List<Room> getUnassignedRooms(Integer dormitoryId) throws  CustomException;
    List<Room> getAllRooms(Integer DormitoryId) throws  CustomException;


}
