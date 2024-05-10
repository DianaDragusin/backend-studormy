package com.example.backendstudormy.domain.service.settings;

import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomRequestDTO;
import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomResponseDTO;
import com.example.backendstudormy.domain.dto.room.getRoom.GetRoomResponseDTO;
import com.example.backendstudormy.domain.dto.settings.GetSettingsResponseDTO;
import com.example.backendstudormy.domain.exceptions.CustomException;

public interface ISettingsService {
    GetSettingsResponseDTO addSetting(Integer adminId, Boolean startAllocation) throws CustomException;
    GetSettingsResponseDTO getSettingByAdminId(Integer adminId) throws CustomException;
    GetSettingsResponseDTO setSetting(Integer adminId, Boolean endAllocation) throws  CustomException;
}
