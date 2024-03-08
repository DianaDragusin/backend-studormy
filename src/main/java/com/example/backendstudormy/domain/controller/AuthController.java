package com.example.backendstudormy.domain.controller;

import com.example.backendstudormy.domain.dto.login.*;
import com.example.backendstudormy.domain.service.IAuthAdminService;
import com.example.backendstudormy.domain.service.IAuthStudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/auth")
public class AuthController {
    private IAuthAdminService authAdminService;
    private IAuthStudentService authStudentService;

    @PostMapping("/login/student")
    public LoginStudentResponseDTO login(@RequestBody LoginStudentRequestDTO loginStudentRequestDTO) {
        return authStudentService.login(loginStudentRequestDTO);
    }
    @PostMapping("/login/admin")
    public LoginAdminResponseDTO login(@RequestBody LoginAdminRequestDTO loginAdminRequestDTO) {
       return authAdminService.login(loginAdminRequestDTO);

    }
}
