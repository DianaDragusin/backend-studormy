package com.example.backendstudormy.domain.dto.student.updateStudent;

import com.example.backendstudormy.domain.entities.Dormitory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UpdateStudentResponseDTO {
    private Integer id;;
    private String email;
    private String password;
    private Integer registrationNumber;
    private Dormitory dormitory;
    private String firstname;
    private String lastname;
    private Date birthday;
    private Integer agreableness;
    private Integer openness ;
    private Integer neuroticism ;
    private Integer concienciousness ;
    private Integer extraversion ;
}
