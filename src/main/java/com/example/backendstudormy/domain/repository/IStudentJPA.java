package com.example.backendstudormy.domain.repository;

import com.example.backendstudormy.domain.entities.Dormitory;
import com.example.backendstudormy.domain.entities.Group;
import com.example.backendstudormy.domain.entities.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentJPA extends JpaRepository<Student,Integer> {
    @Query(" SELECT s FROM Student s ")
    List<Student> findStudentWithPaging(Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.dormitory = :dormitory AND s.id <> :studentId")
    List<Student> findStudentsWithSameDormitoryDifferentThanSelf(@Param("dormitory") Dormitory dormitory, @Param("studentId") Integer studentId);

}