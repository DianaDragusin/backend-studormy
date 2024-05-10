package com.example.backendstudormy.domain.dto.room.getRoom;

import com.example.backendstudormy.domain.entities.Dormitory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetRoomResponseDTO {
    private Integer roomId;
    private Integer roomNumber;
    private Integer maxPeopleNumber;
    private Dormitory dormitory;
}
