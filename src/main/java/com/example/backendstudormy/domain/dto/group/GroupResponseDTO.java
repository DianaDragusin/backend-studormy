package com.example.backendstudormy.domain.dto.group;
import com.example.backendstudormy.domain.dto.group.addGroup.AddGroupResponseDTO;
import com.example.backendstudormy.domain.dto.lessInfoStudent.LessInfoStudent;
import com.example.backendstudormy.domain.entities.Student;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Data
@Getter
@Setter
@NoArgsConstructor
public class GroupResponseDTO {
    private Integer groupId;
    private String name;
    private Integer memberNumber;
    private List<LessInfoStudent> students;

}
