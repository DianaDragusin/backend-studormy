package com.example.backendstudormy.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "settings")
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "settings_ID")
    private Integer settingsId;

    @OneToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Column(name = "room_allocation_started")
    private Boolean roomAllocationStarted;

    @Column(name = "room_allocation_stopped")
    private Boolean roomAllocationStopped;

}
