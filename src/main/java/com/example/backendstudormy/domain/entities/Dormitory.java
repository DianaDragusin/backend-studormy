package com.example.backendstudormy.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
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

    @Column(name="dormitory_name")
    private String name;

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<Room> rooms;

}
