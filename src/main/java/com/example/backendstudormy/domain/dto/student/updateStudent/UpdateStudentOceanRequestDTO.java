package com.example.backendstudormy.domain.dto.student.updateStudent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateStudentOceanRequestDTO {
    private List<Integer> extroversion_responses;
    private List<Integer> openness_responses;
    private List<Integer> neuroticism_responses;
    private List<Integer> agreeableness_responses;
    private List<Integer> conscientiousness_responses;

}
