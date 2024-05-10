package com.example.backendstudormy.domain.dto.room.addRoom;

import com.example.backendstudormy.domain.entities.Dormitory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddRoomResponseDTO {
    private Integer roomId;
    private Integer roomNumber;
    private Integer maxPeopleNumber;
    private Dormitory dormitory;
}
