package com.example.backendstudormy.domain.mapper;

import com.example.backendstudormy.domain.dto.clustering.BigFiveResponses;
import com.example.backendstudormy.domain.dto.clustering.ClusteringRequestDTO;
import com.example.backendstudormy.domain.dto.clustering.ClusteringResponseDTO;
import com.example.backendstudormy.domain.dto.lessInfoStudent.LessInfoStudent;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentOceanResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentResponseDTO;
import com.example.backendstudormy.domain.entities.Dormitory;
import com.example.backendstudormy.domain.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface IStudentMapper {
    List<AddStudentResponseDTO> ListOfStudentsToAddStudentsResponseDtoList(List<Student> students);

    Student updateStudentRequestDtoToStudent(Integer id, UpdateStudentRequestDTO updateStudentRequestDto);
    @Mapping(target = "dormitory", source = "adminDormitory")
    Student addStudentRequestDtoToStudent( Dormitory adminDormitory,AddStudentRequestDTO addStudentRequestDTO);
    ClusteringRequestDTO getStudentResponseDtoToClusteringRequest(GetStudentResponseDTO student);
    GetStudentResponseDTO studentToGetStudentResponseDto(Student student);
    LessInfoStudent studentToLessInfoStudent(Student student);
    List<GetStudentResponseDTO> studentsToGetStudentResponseDtoList(List<Student> students);
    List<GetStudentResponseDTO> studentsToGetStudentResponseDtoSet(Set<Student> students);

    @Mapping(target = "dormitory", source = "student.dormitory.dormitoryId")
    AddStudentResponseDTO studentToAddStudentResponseDto(Student student);
    UpdateStudentResponseDTO studentToUpdateStudentResponseDto(Student student);

    UpdateStudentOceanResponseDTO studentToUpdateStudentOceanResponseDto(Student student);

    List<GetStudentResponseDTO> studentListToGetStudentResponseDtoList(List<Student> students);
     ClusteringRequestDTO studentToClusteringResponseDTO(Student student);
     BigFiveResponses studentToBigFiveResponses(Student student);
    ClusteringRequestDTO BigFiveResponsesToClusteringRequestDTO(BigFiveResponses student);

}
