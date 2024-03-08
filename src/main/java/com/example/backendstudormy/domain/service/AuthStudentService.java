package com.example.backendstudormy.domain.service;

import com.example.backendstudormy.domain.dto.login.LoginAdminRequestDTO;
import com.example.backendstudormy.domain.dto.login.LoginAdminResponseDTO;
import com.example.backendstudormy.domain.dto.login.LoginStudentRequestDTO;
import com.example.backendstudormy.domain.dto.login.LoginStudentResponseDTO;
import com.example.backendstudormy.domain.entities.Admin;
import com.example.backendstudormy.domain.entities.Student;
import com.example.backendstudormy.domain.exceptions.CustomException;
import com.example.backendstudormy.domain.exceptions.ExceptionType;
import com.example.backendstudormy.domain.mapper.ILoginStudentMapper;
import com.example.backendstudormy.domain.repository.IAdminAuthJPA;
import com.example.backendstudormy.domain.repository.IStudentAuthJPA;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthStudentService implements IAuthStudentService{
    private IStudentAuthJPA authStudentRepository;
    private ILoginStudentMapper studentMapper;

    @Override
    public LoginStudentResponseDTO login(LoginStudentRequestDTO loginStudentRequestDto) throws CustomException {
        Student student = authStudentRepository.findByEmailAndPassword(loginStudentRequestDto.getEmail(), loginStudentRequestDto.getPassword());
        if (student == null) {
            throw new CustomException(ExceptionType.CREDENTIAL_ERROR, List.of(loginStudentRequestDto.getEmail()));
        }
        return studentMapper.studentToLoginStudentResponseDto(student);
    }
}
