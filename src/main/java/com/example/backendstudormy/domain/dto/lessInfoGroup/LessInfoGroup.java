package com.example.backendstudormy.domain.dto.lessInfoGroup;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class LessInfoGroup {
    private Integer groupId;
    private String name;
    private Integer memberNumber;
}
