package com.example.backendstudormy.domain.mapper;

import com.example.backendstudormy.domain.dto.room.getRoom.GetRoomResponseDTO;
import com.example.backendstudormy.domain.dto.settings.GetSettingsResponseDTO;
import com.example.backendstudormy.domain.entities.Room;
import com.example.backendstudormy.domain.entities.Settings;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISettingsMapper {
    GetSettingsResponseDTO settingToGetSettingsResponseDto(Settings setting);
}
