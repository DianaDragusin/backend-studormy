package com.example.backendstudormy.domain.repository;

import com.example.backendstudormy.domain.entities.Admin;
import com.example.backendstudormy.domain.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStudentAuthJPA extends JpaRepository<Student,Integer> {
    Optional<Student> findByEmailAndPassword(String email, String password);
}
