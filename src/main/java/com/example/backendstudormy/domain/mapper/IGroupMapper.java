package com.example.backendstudormy.domain.mapper;

import com.example.backendstudormy.domain.dto.group.GroupResponseDTO;
import com.example.backendstudormy.domain.dto.group.addGroup.AddGroupRequestDTO;
import com.example.backendstudormy.domain.dto.group.addGroup.AddGroupResponseDTO;
import com.example.backendstudormy.domain.dto.group.getGroup.GetGroupResponseDTO;
import com.example.backendstudormy.domain.dto.lessInfoGroup.LessInfoGroup;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.entities.Dormitory;
import com.example.backendstudormy.domain.entities.Group;
import com.example.backendstudormy.domain.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface IGroupMapper {
    Group addGroupRequestDtoToGroup(AddGroupRequestDTO addGroupRequestDTO);
    AddGroupResponseDTO groupToAddGroupResponse(Group group);
    @Mapping(target = "roomId", source = "room.roomId")
    LessInfoGroup grouptoLessInfoGroup(Group group);
    @Mapping(target = "roomId", source = "room.roomId")
    GroupResponseDTO groupToGroupResponse(Group group);
    List<GroupResponseDTO >groupsToGroupResponseList(List<Group> groups);
    Set<LessInfoGroup> groupListToLessInfoGroups(Set<Group> groups);

    AddGroupResponseDTO groupToGetGroupResponse(Group group);

}
