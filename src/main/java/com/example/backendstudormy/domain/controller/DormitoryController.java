package com.example.backendstudormy.domain.controller;

import com.example.backendstudormy.domain.dto.dormitory.addDormitory.AddDormitoryResponse;
import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomRequestDTO;
import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomResponseDTO;
import com.example.backendstudormy.domain.service.dormitory.IDormitoryService;
import com.example.backendstudormy.domain.service.room.IRoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/dormitory")
public class DormitoryController {
    private IDormitoryService dormitoryService;
    @PostMapping("/admin/{id}")
    public AddDormitoryResponse addDormitory(@PathVariable Integer id,@RequestBody String name ) {
        return dormitoryService.addDormitory(id,name);
    }

}
