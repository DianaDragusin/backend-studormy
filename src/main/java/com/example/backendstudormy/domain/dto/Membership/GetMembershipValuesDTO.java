package com.example.backendstudormy.domain.dto.Membership;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class GetMembershipValuesDTO {
    private Double cluster0;
    private Double cluster1;
    private Double cluster2;
    private Double cluster3;
    private Double cluster4;
}
