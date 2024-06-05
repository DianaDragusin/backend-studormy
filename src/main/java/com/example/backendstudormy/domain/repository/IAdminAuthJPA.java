package com.example.backendstudormy.domain.repository;

import com.example.backendstudormy.domain.entities.Admin;
import com.example.backendstudormy.domain.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdminAuthJPA extends JpaRepository<Admin,Integer> {
    Admin findByEmailAndPassword(String email, String password);
    Admin findByEmail(String email);
}