package com.example.backendstudormy.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id" , nullable = false)
    private Integer id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name="birthday")
    private Date birthday;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name = "registration_number")
    private Integer registrationNumber;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "dormitory_id" , nullable = false)
    private Dormitory dormitory;

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "university_id")
//    private University university;

    @Column(name = "agreableness")
    private Integer agreableness;

    @Column(name = "open_to_experience")
    private Integer openness ;

    @Column(name = "neuroticism")
    private Integer neuroticism ;

    @Column(name = "concienciousness")
    private Integer concienciousness ;

    @Column(name = "extraversion")
    private Integer extraversion ;



}
