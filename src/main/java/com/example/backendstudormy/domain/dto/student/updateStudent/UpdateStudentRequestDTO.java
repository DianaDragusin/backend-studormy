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
    private Integer id;
    private String email;
    private String password;
    private Integer registrationNumber;
    private Dormitory dormitory;
    private String firstname;
    private String lastname;
    private Date birthday;
    private Double extroversion_score;
    private Double openness_score;
    private Double neuroticism_score;
    private Double agreeableness_score;
    private Double conscientiousness_score;

}