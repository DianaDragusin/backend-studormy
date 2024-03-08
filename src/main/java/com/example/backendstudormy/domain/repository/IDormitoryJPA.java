package com.example.backendstudormy.domain.repository;
import com.example.backendstudormy.domain.entities.Dormitory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDormitoryJPA extends JpaRepository<Dormitory,Integer> {
}
