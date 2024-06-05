package com.example.backendstudormy.domain.mapper;

import com.example.backendstudormy.domain.dto.login.LoginAdminRequestDTO;
import com.example.backendstudormy.domain.dto.login.LoginAdminResponseDTO;
import com.example.backendstudormy.domain.entities.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ISignUpMapper {

    Admin loginAdminRequestDTOToAdmin(LoginAdminRequestDTO loginAdminRequestDTO);
}
