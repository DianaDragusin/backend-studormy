package com.example.backendstudormy.domain.dto.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginStudentRequestDTO {
    private String email;
    private String password;
}
