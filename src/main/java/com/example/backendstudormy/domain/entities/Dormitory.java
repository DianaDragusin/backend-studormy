package com.example.backendstudormy.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dormitory")
public class Dormitory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dormitory_id")
    private Integer dormitoryId;

    @OneToMany
    @JoinColumn(name = "student_id")
    private List<Student> students;

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<Room> rooms;

    @OneToOne(mappedBy = "dormitory", cascade = CascadeType.ALL)
    private Admin admin;
}
