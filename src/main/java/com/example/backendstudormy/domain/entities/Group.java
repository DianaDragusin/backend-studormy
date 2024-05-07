package com.example.backendstudormy.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roommates_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Integer groupId;

    @Column(name="group_name")
    private String name;

    @Column(name="member_number")
    private Integer memberNumber;

    @JsonIgnore
    @ManyToMany(mappedBy = "groups")
    private Set<Student> students ;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;
//    @OneToMany
//    @JoinColumn(name = "student_id")
//    private List<Student> students;

}
