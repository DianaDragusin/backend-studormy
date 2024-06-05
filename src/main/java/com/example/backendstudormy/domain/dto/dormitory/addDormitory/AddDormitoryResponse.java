package com.example.backendstudormy.domain.dto.dormitory.addDormitory;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class AddDormitoryResponse {
    private Integer dormitoryId;
    private String name;
}
