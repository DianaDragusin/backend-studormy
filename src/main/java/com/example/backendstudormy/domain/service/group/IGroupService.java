package com.example.backendstudormy.domain.service.group;

import com.example.backendstudormy.domain.dto.clustering.ClusteringResponseDTO;
import com.example.backendstudormy.domain.dto.group.GroupResponseDTO;
import com.example.backendstudormy.domain.dto.group.addGroup.AddGroupRequestDTO;
import com.example.backendstudormy.domain.dto.group.addGroup.AddGroupResponseDTO;
import com.example.backendstudormy.domain.dto.group.addGroup.AddStudentToGroupRequestDTO;
import com.example.backendstudormy.domain.dto.group.getGroup.GetGroupResponseDTO;
import com.example.backendstudormy.domain.dto.lessInfoGroup.LessInfoGroup;
import com.example.backendstudormy.domain.dto.lessInfoStudent.StudentGroupsDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentOceanRequestDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentOceanResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentResponseDTO;
import com.example.backendstudormy.domain.exceptions.CustomException;

import java.util.List;
public interface IGroupService {
    GroupResponseDTO addGroup(Integer studentId,String groupName) throws CustomException;

    GroupResponseDTO addStudentToGroup(AddStudentToGroupRequestDTO addStudentToGroupRequestDTO) throws CustomException;
  //  AddGroupResponseDTO removeStudentFromGroup(AddStudentToGroupRequestDTO addStudentToGroupRequestDTO) throws CustomException;

    GroupResponseDTO getGroupById(Integer groupId) throws CustomException;
    StudentGroupsDTO getGroupsOfStudent(Integer studentId) throws CustomException;
    void deleteStudentFromGroup(Integer groupId,Integer studentId) throws CustomException;
    GroupResponseDTO applyForARoomWithAGroup(Integer groupId, Integer roomId) throws CustomException;
    Boolean hasRoomAssigned(Integer groupId) throws CustomException;
    List<GroupResponseDTO> getGroupsAssignedToARoom(Integer dormitoryId) throws CustomException;
    void deleteGroupById(Integer groupId) throws CustomException;

}
