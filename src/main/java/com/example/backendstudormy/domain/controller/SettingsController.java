package com.example.backendstudormy.domain.controller;

import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomRequestDTO;
import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomResponseDTO;
import com.example.backendstudormy.domain.dto.room.getRoom.GetRoomResponseDTO;
import com.example.backendstudormy.domain.dto.settings.GetSettingsResponseDTO;
import com.example.backendstudormy.domain.mapper.ISettingsMapper;
import com.example.backendstudormy.domain.service.room.IRoomService;
import com.example.backendstudormy.domain.service.settings.ISettingsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/settings")
public class SettingsController {
    private ISettingsService settingsService;
    @PostMapping("/admin/{id}")
    public GetSettingsResponseDTO addSettingEntity(@PathVariable Integer id, @RequestBody Boolean startAllocation) {
        return settingsService.addSetting(id,startAllocation);
    }
    @GetMapping("/admin/{id}")
    public GetSettingsResponseDTO getSetting(@PathVariable Integer id) {
        return settingsService.getSettingByAdminId(id);
    }
    @PutMapping("/admin/{id}")
    public GetSettingsResponseDTO putSetting(@PathVariable Integer id, @RequestBody Boolean endAllocation) {
        return settingsService.setSetting(id,endAllocation);
    }
}
