package com.example.backendstudormy.domain.repository;

import com.example.backendstudormy.domain.entities.Room;
import com.example.backendstudormy.domain.entities.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISettingsJPA extends JpaRepository<Settings,Integer> {
}
