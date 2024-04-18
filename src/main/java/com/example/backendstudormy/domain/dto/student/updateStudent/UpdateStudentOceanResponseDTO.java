package com.example.backendstudormy.domain.dto.student.updateStudent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateStudentOceanResponseDTO {
    private Integer id;
    private Double extroversion_score;
    private Double openness_score;
    private Double neuroticism_score;
    private Double agreeableness_score;
    private Double conscientiousness_score;

}
