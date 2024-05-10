package com.example.backendstudormy.domain.mapper;

import com.example.backendstudormy.domain.dto.group.addGroup.AddGroupRequestDTO;
import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomRequestDTO;
import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomResponseDTO;
import com.example.backendstudormy.domain.dto.room.getRoom.GetRoomResponseDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.entities.Dormitory;
import com.example.backendstudormy.domain.entities.Room;
import com.example.backendstudormy.domain.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IRoomMapper {

    Room addRoomRequestDtoToRoom(AddRoomRequestDTO addRoomRequestDTO, Dormitory dormitory);

    AddRoomResponseDTO roomToAddRoomResponseDto(Room room);
    GetRoomResponseDTO roomToGetRoomResponseDto(Room room);
    List<GetRoomResponseDTO> roomsToGetRoomResponseDtoList(List<Room> rooms);


}
