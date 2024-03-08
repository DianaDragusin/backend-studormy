package com.example.backendstudormy.domain.dto.student.addStudent;

import com.example.backendstudormy.domain.entities.Dormitory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddStudentResponseDTO {
    private Integer id;
    private String email;
    private String password;
    private Integer registrationNumber;
    private Integer dormitory;
}
