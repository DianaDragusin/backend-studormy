package com.example.backendstudormy.domain.service;

import com.example.backendstudormy.domain.dto.login.LoginAdminRequestDTO;
import com.example.backendstudormy.domain.dto.login.LoginAdminResponseDTO;

public interface IAuthAdminService {
    LoginAdminResponseDTO login(LoginAdminRequestDTO loginAdminRequestDto);
    Integer signUp(LoginAdminRequestDTO signUpAdminRequestDto);

}
