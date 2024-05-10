package com.example.backendstudormy.domain.controller;
import com.example.backendstudormy.domain.dto.admin.getAdmin.GetAdminResponseDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.service.IAdminService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/admin")
public class AdminController {
    private IAdminService adminService;
    @GetMapping("{id}")
    public GetAdminResponseDTO getAdminById(@PathVariable Integer id) {
        return adminService.getAdminById(id);
    }
    @GetMapping("/dormitory/{id}")
    public GetAdminResponseDTO getAdminByDormitoryId(@PathVariable Integer id) {
        return adminService.getAdminByDormitoryId(id);
    }


}
