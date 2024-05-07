package com.example.backendstudormy.domain.dto.lessInfoStudent;

import com.example.backendstudormy.domain.dto.lessInfoGroup.LessInfoGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class StudentGroupsDTO {
    private Integer id;
    private Set<LessInfoGroup> groups ;
}
