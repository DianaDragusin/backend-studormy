package com.example.backendstudormy.domain.dto.login;

import com.example.backendstudormy.domain.entities.Dormitory;
import com.example.backendstudormy.domain.entities.University;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class LoginStudentResponseDTO {
    private Integer studentId;
    private String firstname;
    private String lastname;
    private Date birthday;
    private String email;
    private Integer registrationNumber;
    private Dormitory dormitory;
    private University university;
    private Integer agreableness;
    private Integer openness ;
    private Integer neuroticism ;
    private Integer concienciousness ;
    private Integer extraversion ;
}
