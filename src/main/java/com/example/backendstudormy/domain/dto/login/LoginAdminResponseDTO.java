package com.example.backendstudormy.domain.dto.login;

import com.example.backendstudormy.domain.entities.Dormitory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginAdminResponseDTO {
    private Integer adminId;
    private String email;
    private Dormitory dormitory;
}
