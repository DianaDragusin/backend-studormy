package com.example.backendstudormy.domain.service.group;

import com.example.backendstudormy.domain.dto.group.GroupResponseDTO;
import com.example.backendstudormy.domain.dto.group.addGroup.AddGroupRequestDTO;
import com.example.backendstudormy.domain.dto.group.addGroup.AddGroupResponseDTO;
import com.example.backendstudormy.domain.dto.group.addGroup.AddStudentToGroupRequestDTO;
import com.example.backendstudormy.domain.dto.group.getGroup.GetGroupResponseDTO;
import com.example.backendstudormy.domain.dto.lessInfoStudent.LessInfoStudent;
import com.example.backendstudormy.domain.dto.lessInfoStudent.StudentGroupsDTO;
import com.example.backendstudormy.domain.entities.Group;
import com.example.backendstudormy.domain.entities.Student;
import com.example.backendstudormy.domain.exceptions.CustomException;
import com.example.backendstudormy.domain.exceptions.ExceptionType;
import com.example.backendstudormy.domain.mapper.IGroupMapper;
import com.example.backendstudormy.domain.repository.IGroupJPA;
import com.example.backendstudormy.domain.repository.IStudentJPA;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GroupService implements IGroupService{
    private IGroupJPA groupJPA;
    private IGroupMapper groupMapper;
    private IStudentJPA studentJPA;

   private AddGroupRequestDTO makeAddGroupRequest(String groupName, Student student)
   {
       AddGroupRequestDTO addGroupRequestDto = new AddGroupRequestDTO();
       addGroupRequestDto.setName(groupName);
       addGroupRequestDto.setMemberNumber(1);
       addGroupRequestDto.setStudents(List.of(student));
       return addGroupRequestDto;
   }

    @Override
    public GroupResponseDTO addGroup(Integer studentId, String groupName) throws CustomException {
        Student student = studentJPA.findById(studentId)
                .orElseThrow(() -> new CustomException(ExceptionType.STUDENT_NOT_FOUND, List.of(studentId.toString())));

       AddGroupRequestDTO addGroupRequestDto = makeAddGroupRequest(groupName,student);

        if (groupJPA.findAll().stream().anyMatch(group ->
                group.getName().equals(addGroupRequestDto.getName())))
            throw new CustomException(ExceptionType.DUPLICATE_GROUP_NAMES, List.of(addGroupRequestDto.getName()));

        Group group = groupMapper.addGroupRequestDtoToGroup(addGroupRequestDto);
        // saving the groups in the student entity
        Set<Group> groupsOfStudent = student.getGroups();
        if (groupsOfStudent.isEmpty())
            student.setGroups( new LinkedHashSet<Group>( List.of(group) ));
        else {
            groupsOfStudent.add(group);
            student.setGroups(groupsOfStudent);
        }

        studentJPA.save(student);

        Group groupToAdd = groupJPA.save(group);
        AddGroupResponseDTO addGroupResponseDTO = groupMapper.groupToAddGroupResponse(groupToAdd);
        GroupResponseDTO groupResponseDTO = new GroupResponseDTO();
        this.initialize(groupResponseDTO,addGroupResponseDTO);
        return  groupResponseDTO;
    }

    @Override
    public GroupResponseDTO addStudentToGroup(AddStudentToGroupRequestDTO addStudentToGroupRequestDTO) throws CustomException {
        Group group = groupJPA.findById(addStudentToGroupRequestDTO.getGroupId())
                .orElseThrow(() -> new CustomException(ExceptionType.GROUP_NOT_FOUND, List.of(addStudentToGroupRequestDTO.getGroupId().toString())));

        Student student = studentJPA.findById(addStudentToGroupRequestDTO.getNewMemberId())
                .orElseThrow(() -> new CustomException(ExceptionType.STUDENT_NOT_FOUND, List.of(addStudentToGroupRequestDTO.getNewMemberId().toString())));

        // we will add the group to the student side, so that a student has all the groups it is part of
        Set<Group> groupsOfStudent = student.getGroups();
        group.setMemberNumber(group.getMemberNumber() + 1);
        groupsOfStudent.add(group);
        student.setGroups( groupsOfStudent);
        studentJPA.save(student);

        Group groupToAdd = groupJPA.save(group);

        AddGroupResponseDTO addGroupResponseDTO =  groupMapper.groupToAddGroupResponse(groupToAdd);
        GroupResponseDTO groupResponseDTO = new GroupResponseDTO();
        this.initialize(groupResponseDTO,addGroupResponseDTO);
        return  groupResponseDTO;
    }

    @Override
    public GroupResponseDTO getGroupById(Integer groupId) throws CustomException {
        Optional<Group> group = groupJPA.findById(groupId);
        if (group.isEmpty()) {
            throw new CustomException(ExceptionType.ID_NOT_FOUND, List.of(groupId.toString()));
        }

        AddGroupResponseDTO addGroupResponseDTO =  groupMapper.groupToGetGroupResponse(group.get());
        GroupResponseDTO groupResponseDTO = new GroupResponseDTO();
        this.initialize(groupResponseDTO,addGroupResponseDTO);
        return  groupResponseDTO;
    }
   private Set<Group> findStudentInGroupJpaAndGiveItsGroups(Integer studentId){
       Optional<Student> studentFound = groupJPA.findAll().stream()
               .flatMap(group -> group.getStudents().stream())
               .filter(student -> student.getId().equals(studentId))
               .findFirst();

       Set<Group> groups  = new HashSet<>();
       if (studentFound.isPresent()) {
           groups = groupJPA.findAll().stream()
                   .filter(group -> group.getStudents().contains(studentFound.get()))
                   .collect(Collectors.toSet());
       }
       return groups;
   }

    private void findStudentThatHasGroupAndDeleteGroup(Integer groupId){
        Optional<Student> studentFound = studentJPA.findAll().stream()
                .filter(stud -> stud.getGroups().stream()
                        .anyMatch(group -> group.getGroupId().equals(groupId)))
                .findFirst();

        if (studentFound.isPresent()) {
            Student student = studentFound.get();
            // Remove the group with the specified groupId from the student's list of groups
            student.getGroups().removeIf(group -> group.getGroupId().equals(groupId));
            // Save the updated student back to the JPA repository
            studentJPA.save(student);
        }
    }
    @Override
    public StudentGroupsDTO getGroupsOfStudent(Integer studentId) throws CustomException {
        Set<Group> groups  = findStudentInGroupJpaAndGiveItsGroups(studentId);
        StudentGroupsDTO studentGroupsDTO= new StudentGroupsDTO();
        studentGroupsDTO.setGroups(groupMapper.groupListToLessInfoGroups(groups));
        studentGroupsDTO.setId(studentId);

        return  studentGroupsDTO;
    }

    @Override
    public void deleteStudentFromGroup(Integer groupId, Integer studentId) throws CustomException {
        Group group = groupJPA.findById(groupId)
                .orElseThrow(() -> new CustomException(ExceptionType.GROUP_NOT_FOUND, List.of(groupId.toString())));

        Student student = studentJPA.findById(studentId)
                .orElseThrow(() -> new CustomException(ExceptionType.STUDENT_NOT_FOUND, List.of(studentId.toString())));


        if (group.getMemberNumber() > 1) {
            Group groupToBeRemoved = (Group) student.getGroups().stream()
                    .filter(studentGroup -> studentGroup.getGroupId().equals(groupId))
                    .findAny()
                    .orElse(null);
            if (groupToBeRemoved != null)
            {
                groupToBeRemoved.setMemberNumber(groupToBeRemoved.getMemberNumber() - 1);
                groupJPA.save(groupToBeRemoved);
                // remove the group from  the students list of groups
                student.getGroups().remove(groupToBeRemoved);
                studentJPA.save(student);

            }

        }else throw new CustomException(ExceptionType.STUDENT_CAN_NOT_BE_REMOVED_FROM_GROUP, List.of(studentId.toString()));

    }

    @Override
    public void deleteGroupById(Integer groupId) throws CustomException {
        Optional<Group> group = groupJPA.findById(groupId);
        if (group.isEmpty()) {
            throw new CustomException(ExceptionType.ID_NOT_FOUND, List.of(groupId.toString()));
        }
        if (group.get().getMemberNumber() == 1) {
            findStudentThatHasGroupAndDeleteGroup(groupId);
            groupJPA.deleteById(groupId);

        }else throw new CustomException(ExceptionType.GROUP_WITH_MORE_THAN_ONE_MEMBER, List.of(groupId.toString()));
    }


    private void initialize(GroupResponseDTO groupResponseDTO,AddGroupResponseDTO addGroupResponseDTO){
        groupResponseDTO.setGroupId(addGroupResponseDTO.getGroupId());
        groupResponseDTO.setName( addGroupResponseDTO.getName());
        groupResponseDTO.setMemberNumber(addGroupResponseDTO.getMemberNumber());
        List<LessInfoStudent> lessStudentList = new ArrayList<>();
        List<Student> studentList = addGroupResponseDTO.getStudents();
        for (Student student : studentList)
        {
            LessInfoStudent lessInfoStudent = new LessInfoStudent();
            lessInfoStudent.setId(student.getId());
            lessInfoStudent.setEmail(student.getEmail());
            lessInfoStudent.setFirstname(student.getFirstname());
            lessInfoStudent.setLastname(student.getLastname());
            lessStudentList.add(lessInfoStudent);
        }
        groupResponseDTO.setStudents(lessStudentList);
    }
}
