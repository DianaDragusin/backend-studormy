package com.example.backendstudormy.domain.dto.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginAdminRequestDTO {
    private String email;
    private String password;
}
