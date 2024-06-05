package com.example.backendstudormy.domain.mapper;


import com.example.backendstudormy.domain.dto.dormitory.addDormitory.AddDormitoryResponse;
import com.example.backendstudormy.domain.dto.group.addGroup.AddGroupResponseDTO;
import com.example.backendstudormy.domain.entities.Dormitory;
import com.example.backendstudormy.domain.entities.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IDormitoryMapper {
    @Mapping(target = "name", source = "name")
    Dormitory nameToDormitory(String name);
    AddDormitoryResponse dormitoryToAddDormitoryResponse(Dormitory dormitory);
}
