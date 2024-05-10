package com.example.backendstudormy.domain.service.room;

import com.example.backendstudormy.domain.dto.group.GroupResponseDTO;
import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomRequestDTO;
import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomResponseDTO;
import com.example.backendstudormy.domain.dto.room.getRoom.GetRoomResponseDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.exceptions.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface IRoomService {

    AddRoomResponseDTO addRoom(Integer dormitoryId, AddRoomRequestDTO addRoomRequestDTO) throws CustomException;
    GetRoomResponseDTO getRoomById(Integer roomId) throws CustomException;
    List<GetRoomResponseDTO> getAllRooms(Integer dormitoryId) throws CustomException;
    Integer getMaxRoomNr(Integer dormitoryId) throws CustomException;


}
