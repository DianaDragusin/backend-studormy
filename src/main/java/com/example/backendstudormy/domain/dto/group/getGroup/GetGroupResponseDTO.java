package com.example.backendstudormy.domain.dto.group.getGroup;

import com.example.backendstudormy.domain.entities.Student;

import java.util.List;

public class GetGroupResponseDTO {
    private Integer groupId;
    private String name;
    private Integer memberNumber;
    private List<Student> students;
}
