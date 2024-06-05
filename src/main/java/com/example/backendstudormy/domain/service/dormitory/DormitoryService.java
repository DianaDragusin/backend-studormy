package com.example.backendstudormy.domain.service.dormitory;

import com.example.backendstudormy.domain.dto.dormitory.addDormitory.AddDormitoryResponse;
import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomResponseDTO;
import com.example.backendstudormy.domain.entities.Admin;
import com.example.backendstudormy.domain.entities.Dormitory;
import com.example.backendstudormy.domain.entities.Room;
import com.example.backendstudormy.domain.exceptions.CustomException;
import com.example.backendstudormy.domain.exceptions.ExceptionType;
import com.example.backendstudormy.domain.mapper.IDormitoryMapper;
import com.example.backendstudormy.domain.mapper.IRoomMapper;
import com.example.backendstudormy.domain.repository.IAdminJPA;
import com.example.backendstudormy.domain.repository.IDormitoryJPA;
import com.example.backendstudormy.domain.repository.IGroupJPA;
import com.example.backendstudormy.domain.repository.IRoomJPA;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DormitoryService implements IDormitoryService{
    private IDormitoryJPA dormitoryJPA;
    private IDormitoryMapper dormitoryMapper;
    private IAdminJPA adminJPA;

    @Override
    public AddDormitoryResponse addDormitory(Integer adminId,String name) throws CustomException {
        Dormitory dormitory = dormitoryJPA.findByName(name);
        if (dormitory != null){
              throw  new CustomException(ExceptionType.DORMITORY_ALREADY_EXISTS);
          }
        Optional<Admin> adminFound = adminJPA.findById(adminId);
        if (adminFound.isEmpty()) {
            throw new CustomException(ExceptionType.ADMIN_NOT_FOUND, List.of(adminId.toString()));
        }
        Admin admin = adminFound.get();
        Dormitory dormitory1 = dormitoryJPA.save( dormitoryMapper.nameToDormitory(name));
        admin.setDormitory(dormitory1);
        adminJPA.save(admin);
        return  dormitoryMapper.dormitoryToAddDormitoryResponse(dormitory1);
    }
}
