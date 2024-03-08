package com.example.backendstudormy.domain.controller;
import com.example.backendstudormy.domain.dto.login.LoginAdminRequestDTO;
import com.example.backendstudormy.domain.dto.login.LoginAdminResponseDTO;
import com.example.backendstudormy.domain.dto.login.LoginStudentRequestDTO;
import com.example.backendstudormy.domain.dto.login.LoginStudentResponseDTO;
import com.example.backendstudormy.domain.service.IAdminService;
import com.example.backendstudormy.domain.service.IAuthAdminService;
import com.example.backendstudormy.domain.service.IAuthStudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/admin")
public class AdminController {


}
