package com.example.backendstudormy.domain.controller;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentResponseDTO;
import com.example.backendstudormy.domain.service.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/student")
public class StudentController {
    private IStudentService studentService;

    @PostMapping("/uploadStudents/{id}")
    public List<AddStudentResponseDTO> uploadStudents(@PathVariable Integer id,@RequestBody List<AddStudentRequestDTO> uploadStudentsRequestDTOList) {
        System.out.println(uploadStudentsRequestDTOList.toString());
        return studentService.uploadStudents(id,uploadStudentsRequestDTOList);
    }
}
