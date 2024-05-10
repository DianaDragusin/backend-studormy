package com.example.backendstudormy.domain.dto.admin.getAdmin;

import com.example.backendstudormy.domain.entities.Dormitory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class GetAdminResponseDTO {
    private Integer id;
    private String email;
    private Dormitory dormitory;

}
