package com.example.backendstudormy.domain.controller;

import com.example.backendstudormy.domain.dto.group.GroupResponseDTO;
import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomRequestDTO;
import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomResponseDTO;
import com.example.backendstudormy.domain.dto.room.getRoom.GetRoomResponseDTO;
import com.example.backendstudormy.domain.service.group.IGroupService;
import com.example.backendstudormy.domain.service.room.IRoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/rooms")
public class RoomController {
    private IRoomService roomService;
    @PostMapping("/dormitory/{id}")
    public AddRoomResponseDTO addRoom(@PathVariable Integer id, @RequestBody AddRoomRequestDTO addRoomRequestDTO) {
        return roomService.addRoom(id,addRoomRequestDTO);
    }
    @GetMapping("{id}")
    public GetRoomResponseDTO getRoomById(@PathVariable Integer id) {
        return roomService.getRoomById(id);
    }
    @GetMapping("/max/{id}")
    public Integer getMaxRoomNr(@PathVariable Integer id) {
        return roomService.getMaxRoomNr(id);
    }
    @GetMapping("/maxCapacity/{id}")
    public Integer getMaxCapacity(@PathVariable Integer id) {
        return roomService.getMaxCapacity(id);
    }

    @GetMapping("/dormitory/{id}")
    public List<GetRoomResponseDTO> getAllRooms(@PathVariable Integer id) {
        return roomService.getAllRooms(id);
    }
    @GetMapping("vacant/dormitory/{id}")
    public List<GetRoomResponseDTO> getAllVacantRooms(@PathVariable Integer id) {
        return roomService.getAllVacantRooms(id);
    }

}
