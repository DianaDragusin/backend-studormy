package com.example.backendstudormy.domain.mapper;


import com.example.backendstudormy.domain.dto.login.LoginAdminResponseDTO;
import com.example.backendstudormy.domain.entities.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ILoginAdminMapper {
    LoginAdminResponseDTO adminToLoginAdminResponseDto(Admin admin);

}
