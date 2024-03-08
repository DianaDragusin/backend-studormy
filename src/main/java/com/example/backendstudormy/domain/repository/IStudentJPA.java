package com.example.backendstudormy.domain.repository;

import com.example.backendstudormy.domain.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentJPA extends JpaRepository<Student,Integer> {
}
