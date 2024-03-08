//package com.example.backendstudormy.domain.entities;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "specialization")
//public class Specialization {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "specialization_id")
//    private Integer specializationId;
//
//    @Column(name = "name")
//    private String name;
//
//    @ManyToMany
//    @JoinTable(
//            name = "specialization_language",
//            joinColumns = @JoinColumn(name = "specialization_id"),
//            inverseJoinColumns = @JoinColumn(name = "language_id"))
//    private List<Language> languages;
//}
//
