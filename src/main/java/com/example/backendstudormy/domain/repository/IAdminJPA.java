package com.example.backendstudormy.domain.repository;

import com.example.backendstudormy.domain.entities.Admin;
import com.example.backendstudormy.domain.entities.Dormitory;
import com.example.backendstudormy.domain.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminJPA extends JpaRepository<Admin,Integer> {
    Admin findByDormitory(Dormitory dormitory);

}
