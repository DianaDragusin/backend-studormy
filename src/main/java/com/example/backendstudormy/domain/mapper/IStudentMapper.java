package com.example.backendstudormy.domain.mapper;

import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentResponseDTO;
import com.example.backendstudormy.domain.entities.Dormitory;
import com.example.backendstudormy.domain.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IStudentMapper {
    List<AddStudentResponseDTO> ListOfStudentsToAddStudentsResponseDtoList(List<Student> students);

    Student updateStudentRequestDtoToStudent(Integer id, UpdateStudentRequestDTO updateStudentRequestDto);
    @Mapping(target = "dormitory", source = "adminDormitory")
    Student addStudentRequestDtoToStudent( Dormitory adminDormitory,AddStudentRequestDTO addStudentRequestDTO);

    GetStudentResponseDTO studentToGetStudentResponseDto(Student student);
    @Mapping(target = "dormitory", source = "student.dormitory.dormitoryId")
    AddStudentResponseDTO studentToAddStudentResponseDto(Student student);
    UpdateStudentResponseDTO studentToUpdateStudentResponseDto(Student student);
    List<GetStudentResponseDTO> studentListToGetStudentResponseDtoList(List<Student> students);

}
