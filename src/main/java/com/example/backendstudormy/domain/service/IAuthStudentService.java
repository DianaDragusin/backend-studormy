package com.example.backendstudormy.domain.service;

import com.example.backendstudormy.domain.dto.login.LoginAdminRequestDTO;
import com.example.backendstudormy.domain.dto.login.LoginAdminResponseDTO;
import com.example.backendstudormy.domain.dto.login.LoginStudentRequestDTO;
import com.example.backendstudormy.domain.dto.login.LoginStudentResponseDTO;

public interface IAuthStudentService {
      LoginStudentResponseDTO login(LoginStudentRequestDTO loginStudentRequestDto);
}
