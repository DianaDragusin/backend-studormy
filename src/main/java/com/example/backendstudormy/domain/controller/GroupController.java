package com.example.backendstudormy.domain.controller;

import com.example.backendstudormy.domain.dto.group.GroupResponseDTO;
import com.example.backendstudormy.domain.dto.group.addGroup.AddGroupResponseDTO;
import com.example.backendstudormy.domain.dto.group.addGroup.AddStudentToGroupRequestDTO;
import com.example.backendstudormy.domain.dto.group.getGroup.GetGroupResponseDTO;
import com.example.backendstudormy.domain.dto.lessInfoStudent.StudentGroupsDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentOceanRequestDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentOceanResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentResponseDTO;
import com.example.backendstudormy.domain.service.IStudentService;
import com.example.backendstudormy.domain.service.group.IGroupService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/groups")
public class GroupController {
    private IGroupService groupService;
    @PostMapping("/{id}")
    public GroupResponseDTO addGroup(@PathVariable Integer id, @RequestBody String groupName) {
        return groupService.addGroup(id,groupName);
    }

    @PutMapping("/addStudentToGroup")
    public GroupResponseDTO addStudentToGroup(@RequestBody AddStudentToGroupRequestDTO addStudentToGroupRequestDTO) {
        return groupService.addStudentToGroup(addStudentToGroupRequestDTO);
    }

    @GetMapping("{id}")
    public GroupResponseDTO getGroupById(@PathVariable Integer id) {
        return groupService.getGroupById(id);
    }

    @GetMapping("ofStudent/{id}")
    public StudentGroupsDTO getStudentGroups(@PathVariable Integer id) {
        return groupService.getGroupsOfStudent(id);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Integer id) {
        groupService.deleteGroupById(id);
    }

    @PutMapping("/{groupId}/students/{studentId}")
    public void removeMemberFromGroup(@PathVariable Integer groupId, @PathVariable Integer studentId) {
        groupService.deleteStudentFromGroup(groupId,studentId);
    }
}
