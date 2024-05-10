package com.example.backendstudormy.domain.dto.room.addRoom;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddRoomRequestDTO  {
    private Integer roomNumber;
    private Integer maxPeopleNumber;
}
