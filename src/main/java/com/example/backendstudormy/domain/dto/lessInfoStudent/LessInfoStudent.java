package com.example.backendstudormy.domain.dto.lessInfoStudent;

import com.example.backendstudormy.domain.entities.Dormitory;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@Data
public class LessInfoStudent {
    private Integer id;
    private String email;
    private String firstname;
    private String lastname;

}
