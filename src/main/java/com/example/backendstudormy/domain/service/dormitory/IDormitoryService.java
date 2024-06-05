package com.example.backendstudormy.domain.service.dormitory;

import com.example.backendstudormy.domain.dto.dormitory.addDormitory.AddDormitoryResponse;
import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomRequestDTO;
import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomResponseDTO;
import com.example.backendstudormy.domain.exceptions.CustomException;
import com.example.backendstudormy.domain.mapper.IRoomMapper;
import com.example.backendstudormy.domain.repository.IDormitoryJPA;
import com.example.backendstudormy.domain.repository.IGroupJPA;
import com.example.backendstudormy.domain.repository.IRoomJPA;

public interface IDormitoryService {


    AddDormitoryResponse addDormitory(Integer adminId,String name) throws CustomException;

}
