package com.example.backendstudormy.domain.dto.clustering;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClusteringRequestDTO {
    private double agreeableness_score;
    private double openness_score ;
    private double neuroticism_score ;
    private double conscientiousness_score ;
    private double extroversion_score ;
}


