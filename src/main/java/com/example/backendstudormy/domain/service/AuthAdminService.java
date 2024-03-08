package com.example.backendstudormy.domain.service;

import com.example.backendstudormy.domain.dto.login.LoginAdminRequestDTO;
import com.example.backendstudormy.domain.dto.login.LoginAdminResponseDTO;
import com.example.backendstudormy.domain.dto.login.LoginStudentRequestDTO;
import com.example.backendstudormy.domain.dto.login.LoginStudentResponseDTO;
import com.example.backendstudormy.domain.entities.Admin;
import com.example.backendstudormy.domain.entities.Student;
import com.example.backendstudormy.domain.exceptions.CustomException;
import com.example.backendstudormy.domain.exceptions.ExceptionType;
import com.example.backendstudormy.domain.mapper.ILoginAdminMapper;
import com.example.backendstudormy.domain.repository.IAdminAuthJPA;
import com.example.backendstudormy.domain.repository.IStudentAuthJPA;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthAdminService implements IAuthAdminService{
    private IAdminAuthJPA authAdminRepository;
    private ILoginAdminMapper adminMapper;
    @Override
    public LoginAdminResponseDTO login(LoginAdminRequestDTO loginAdminRequestDto) throws CustomException {
        Admin admin = authAdminRepository.findByEmailAndPassword(loginAdminRequestDto.getEmail(), loginAdminRequestDto.getPassword());
        if (admin == null) {
            throw new CustomException(ExceptionType.CREDENTIAL_ERROR, List.of(loginAdminRequestDto.getEmail()));
        }
        return adminMapper.adminToLoginAdminResponseDto(admin);

    }

}
