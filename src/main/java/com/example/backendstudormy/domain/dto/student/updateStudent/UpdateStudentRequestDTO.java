package com.example.backendstudormy.domain.dto.student.updateStudent;

import com.example.backendstudormy.domain.entities.Dormitory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UpdateStudentRequestDTO {
    private String email;
    private String firstname;
    private String lastname;
    private Date birthday;
}
