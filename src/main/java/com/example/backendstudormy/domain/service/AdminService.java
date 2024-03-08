package com.example.backendstudormy.domain.service;
import com.example.backendstudormy.domain.exceptions.CustomException;
import com.example.backendstudormy.domain.mapper.IAdminMapper;
import com.example.backendstudormy.domain.mapper.ILoginStudentMapper;
import com.example.backendstudormy.domain.repository.IAdminJPA;
import com.example.backendstudormy.domain.repository.IStudentAuthJPA;
import com.example.backendstudormy.domain.repository.IStudentJPA;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminService implements IAdminService {

}
