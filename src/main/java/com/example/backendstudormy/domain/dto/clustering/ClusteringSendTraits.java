package com.example.backendstudormy.domain.dto.clustering;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClusteringSendTraits {
    private Double agreeableness_score;
    private Double openness_score ;
    private Double neuroticism_score ;
    private Double conscientiousness_score ;
    private Double extroversion_score ;
}
