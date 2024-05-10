package com.example.backendstudormy.domain.repository;

import com.example.backendstudormy.domain.entities.Group;
import com.example.backendstudormy.domain.entities.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoomJPA  extends JpaRepository<Room,Integer> {
    @Query(" SELECT r FROM Student r ")
    List<Group> findRoomsWithPaging (Pageable pageable);

}
