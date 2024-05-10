package com.example.backendstudormy.domain.service.settings;

import com.example.backendstudormy.domain.dto.settings.GetSettingsResponseDTO;
import com.example.backendstudormy.domain.entities.Admin;
import com.example.backendstudormy.domain.entities.Settings;
import com.example.backendstudormy.domain.exceptions.CustomException;
import com.example.backendstudormy.domain.exceptions.ExceptionType;
import com.example.backendstudormy.domain.mapper.ISettingsMapper;
import com.example.backendstudormy.domain.repository.IAdminJPA;
import com.example.backendstudormy.domain.repository.ISettingsJPA;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SettingsService implements ISettingsService{
    private ISettingsMapper settingsMapper;
    private ISettingsJPA settingsJPA;
    private IAdminJPA adminJPA;
    @Override
    public GetSettingsResponseDTO addSetting(Integer adminId, Boolean startAllocation) throws CustomException {
        Admin admin = adminJPA.findById(adminId)
                .orElseThrow(() -> new CustomException(ExceptionType.ADMIN_NOT_FOUND, List.of(adminId.toString())));

        Optional<Settings> setting = settingsJPA.findAll().stream()
                .filter(s -> s.getAdmin().equals(admin))
                .findFirst();
        Settings settingToSave =  new Settings();
        if (setting.isEmpty())
        {
            settingToSave.setAdmin(admin);
            settingToSave.setRoomAllocationStarted(startAllocation);
            settingToSave.setRoomAllocationStopped(Boolean.FALSE);
            settingsJPA.save(settingToSave);
        }

        return settingsMapper.settingToGetSettingsResponseDto(settingToSave);
    }

    @Override
    public GetSettingsResponseDTO getSettingByAdminId(Integer adminId) throws CustomException {
        Admin admin = adminJPA.findById(adminId)
                .orElseThrow(() -> new CustomException(ExceptionType.ADMIN_NOT_FOUND, List.of(adminId.toString())));
        Optional<Settings> setting = settingsJPA.findAll().stream()
                .filter(s -> s.getAdmin().equals(admin))
                .findFirst();
        if (setting.isEmpty())
        {
           return settingsMapper.settingToGetSettingsResponseDto(new Settings());
        }
        return settingsMapper.settingToGetSettingsResponseDto(setting.get());
    }

    @Override
    public GetSettingsResponseDTO setSetting(Integer adminId, Boolean endAllocation) throws CustomException {
        Admin admin = adminJPA.findById(adminId)
                .orElseThrow(() -> new CustomException(ExceptionType.ADMIN_NOT_FOUND, List.of(adminId.toString())));

        Optional<Settings> setting = settingsJPA.findAll().stream()
                .filter(s -> s.getAdmin().equals(admin))
                .findFirst();
        if (setting.isEmpty())
        {
            throw new CustomException(ExceptionType.STOP_BEFORE_START);
        }
        Settings settingsToChange = setting.get();
        if (!settingsToChange.getRoomAllocationStarted() )
        {
            throw new CustomException(ExceptionType.STOP_BEFORE_START);
        }
        settingsToChange.setRoomAllocationStopped(endAllocation);
        settingsJPA.save(settingsToChange);
        return settingsMapper.settingToGetSettingsResponseDto(settingsToChange);

    }
}
