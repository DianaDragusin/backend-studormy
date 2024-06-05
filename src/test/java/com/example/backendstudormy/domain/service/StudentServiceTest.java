package com.example.backendstudormy.domain.service;

import com.example.backendstudormy.domain.mapper.IStudentMapper;
import com.example.backendstudormy.domain.repository.IAdminJPA;
import com.example.backendstudormy.domain.repository.IDormitoryJPA;
import com.example.backendstudormy.domain.repository.IStudentJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    @Mock
    private IAdminJPA mockAdminJPA;

    @Mock
    private IStudentJPA mockStudentJPA;

    @Mock
    private IStudentMapper mockStudentMapper;

    @Mock
    private IDormitoryJPA mockDormitoryJPA;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void uploadStudents() {
    }



    @Test
    void addStudent_ValidAdminId_ValidStudentData() {
        // valid admin ID und valid student data

        StudentService studentService ;
    }

    @Test
    void updateAsset() {
    }

    @Test
    void getStudentById() {
    }

    @Test
    void deleteAssetById() {
    }
}