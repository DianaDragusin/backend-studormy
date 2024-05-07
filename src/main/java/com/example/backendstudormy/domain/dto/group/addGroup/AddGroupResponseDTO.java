package com.example.backendstudormy.domain.dto.group.addGroup;

import com.example.backendstudormy.domain.entities.Student;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Data
@NoArgsConstructor
public class AddGroupResponseDTO {
    private Integer groupId;
    private String name;
    private Integer memberNumber;
    private List<Student> students;
}
