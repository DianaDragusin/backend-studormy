package com.example.backendstudormy.domain.service;
import com.example.backendstudormy.domain.dto.admin.getAdmin.GetAdminResponseDTO;
import com.example.backendstudormy.domain.entities.Admin;
import com.example.backendstudormy.domain.entities.Dormitory;
import com.example.backendstudormy.domain.entities.Student;
import com.example.backendstudormy.domain.exceptions.CustomException;
import com.example.backendstudormy.domain.exceptions.ExceptionType;
import com.example.backendstudormy.domain.mapper.IAdminMapper;
import com.example.backendstudormy.domain.mapper.ILoginStudentMapper;
import com.example.backendstudormy.domain.mapper.IStudentMapper;
import com.example.backendstudormy.domain.repository.IAdminJPA;
import com.example.backendstudormy.domain.repository.IDormitoryJPA;
import com.example.backendstudormy.domain.repository.IStudentAuthJPA;
import com.example.backendstudormy.domain.repository.IStudentJPA;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminService implements IAdminService {
    private IAdminJPA adminJPA;
    private IDormitoryJPA dormitoryJPA;
    private IAdminMapper adminMapper;

    @Override
    public GetAdminResponseDTO getAdminById(Integer adminId) throws CustomException {
        Optional<Admin> admin = adminJPA.findById(adminId);
        if (admin.isEmpty()) {
            throw new CustomException(ExceptionType.ADMIN_NOT_FOUND, List.of(adminId.toString()));
        }

        return adminMapper.adminToGetAdminResponseDto(admin.get());

    }

    @Override
    public GetAdminResponseDTO getAdminByDormitoryId(Integer dormitoryId) throws CustomException {
        Admin admin = null;
        Optional<Dormitory> dormitory = dormitoryJPA.findById(dormitoryId);
        if (dormitory.isEmpty()) {
            throw new CustomException(ExceptionType.DORMITORY_NOT_FOUND, List.of(dormitoryId.toString()));
        }
        else{
            Dormitory adminsDorm = dormitory.get();
            admin = adminJPA.findByDormitory(adminsDorm);
            if (admin == null)
            {
                throw new CustomException(ExceptionType.ADMIN_NOT_FOUND, List.of(dormitoryId.toString()));

            }
        }


        return adminMapper.adminToGetAdminResponseDto(admin);

    }
}
