package com.example.backendstudormy.domain.dto.settings;

import com.example.backendstudormy.domain.entities.Admin;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetSettingsResponseDTO {

    private Integer settingsId;

    private Admin admin;

    private Boolean roomAllocationStarted;

    private Boolean roomAllocationStopped;
}
