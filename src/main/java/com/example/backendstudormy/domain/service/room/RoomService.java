package com.example.backendstudormy.domain.service.room;

import com.example.backendstudormy.domain.dto.group.GroupResponseDTO;
import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomRequestDTO;
import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomResponseDTO;
import com.example.backendstudormy.domain.dto.room.getRoom.GetRoomResponseDTO;
import com.example.backendstudormy.domain.entities.Dormitory;
import com.example.backendstudormy.domain.entities.Room;
import com.example.backendstudormy.domain.entities.Student;
import com.example.backendstudormy.domain.exceptions.CustomException;
import com.example.backendstudormy.domain.exceptions.ExceptionType;
import com.example.backendstudormy.domain.mapper.IGroupMapper;
import com.example.backendstudormy.domain.mapper.IRoomMapper;
import com.example.backendstudormy.domain.repository.IDormitoryJPA;
import com.example.backendstudormy.domain.repository.IGroupJPA;
import com.example.backendstudormy.domain.repository.IRoomJPA;
import com.example.backendstudormy.domain.repository.IStudentJPA;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService implements IRoomService{
    private IRoomJPA roomJPA;
    private IRoomMapper roomMapper;
    private IDormitoryJPA dormitoryJPA;


    @Override
    public AddRoomResponseDTO addRoom(Integer dormitoryId, AddRoomRequestDTO addRoomRequestDTO) throws CustomException {
        Dormitory dormitory = dormitoryJPA.findById(dormitoryId)
                .orElseThrow(() -> new CustomException(ExceptionType.DORMITORY_NOT_FOUND, List.of(dormitoryId.toString())));

        Room roomToSave = roomMapper.addRoomRequestDtoToRoom(addRoomRequestDTO,dormitory);
        Room room = roomJPA.save(roomToSave);
        dormitory.getRooms().add(room);
        dormitoryJPA.save(dormitory);
        return roomMapper.roomToAddRoomResponseDto(room);

    }

    @Override
    public GetRoomResponseDTO getRoomById(Integer roomId) throws CustomException {
        Optional<Room> room = roomJPA.findById(roomId);
        if (room.isEmpty()) {
            throw new CustomException(ExceptionType.ID_NOT_FOUND, List.of(roomId.toString()));
        }

        return roomMapper.roomToGetRoomResponseDto(room.get());

    }

    @Override
    public List<GetRoomResponseDTO> getAllRooms(Integer dormitoryId) throws CustomException {
        Optional<Dormitory> dormitory = dormitoryJPA.findById(dormitoryId);
        if (dormitory.isEmpty()) {
            throw new CustomException(ExceptionType.ID_NOT_FOUND, List.of(dormitoryId.toString()));
        }
        List<Room> rooms =dormitory.get().getRooms();
        if (rooms.isEmpty())
        {
            throw new CustomException(ExceptionType.NO_ROOM_FOUND);
        }

        return roomMapper.roomsToGetRoomResponseDtoList(rooms);
    }

    @Override
    public Integer getMaxRoomNr(Integer dormitoryId) throws CustomException {
        Optional<Dormitory> dormitory = dormitoryJPA.findById(dormitoryId);
        if (dormitory.isEmpty()) {
            throw new CustomException(ExceptionType.ID_NOT_FOUND, List.of(dormitoryId.toString()));
        }
        List<Room> rooms =dormitory.get().getRooms();
        return rooms.stream()
                .mapToInt(Room::getRoomNumber)
                .max()
                .orElse(0);
    }
}
