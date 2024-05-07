package com.example.backendstudormy.domain.service;

import com.example.backendstudormy.domain.dto.clustering.ClusteringRequestDTO;
import com.example.backendstudormy.domain.dto.clustering.ClusteringResponseDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentOceanRequestDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentOceanResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentResponseDTO;
import com.example.backendstudormy.domain.entities.Admin;
import com.example.backendstudormy.domain.entities.Dormitory;
import com.example.backendstudormy.domain.entities.Student;
import com.example.backendstudormy.domain.exceptions.CustomException;
import com.example.backendstudormy.domain.exceptions.ExceptionType;
import com.example.backendstudormy.domain.mapper.IAdminMapper;
import com.example.backendstudormy.domain.mapper.IStudentMapper;
import com.example.backendstudormy.domain.repository.IAdminJPA;
import com.example.backendstudormy.domain.repository.IDormitoryJPA;
import com.example.backendstudormy.domain.repository.IStudentJPA;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StudentService implements IStudentService{

    private IAdminJPA adminJPA;
    private IStudentJPA studentJPA;
    private IStudentMapper studentMapper;
    private IDormitoryJPA dormitoryJPA;
    public static Integer numberStudents;

    @Override
    public List<AddStudentResponseDTO> uploadStudents(Integer adminId, List<AddStudentRequestDTO> addStudentRequestDTOList) throws CustomException {
        List<AddStudentResponseDTO> addStudentResponseDTOS = new ArrayList<>();
        numberStudents = addStudentRequestDTOList.size();
        for (AddStudentRequestDTO addStudentRequestDTO : addStudentRequestDTOList)
        {
            try {
                addStudentResponseDTOS.add(addStudent(adminId,addStudentRequestDTO));
            } catch (CustomException e) {
                numberStudents --;
            }
        }
        if ( numberStudents != addStudentRequestDTOList.size())
            throw new CustomException(ExceptionType.MULTIPLE_ERRORS_UPLOAD, List.of (Integer.toString(addStudentRequestDTOList.size()- numberStudents)));
       return addStudentResponseDTOS;
    }

    @Override
    public AddStudentResponseDTO addStudent(Integer adminId,AddStudentRequestDTO addStudentRequestDto) throws CustomException {
        //System.out.println("am intrat in addstudent");
        if (studentJPA.findAll().stream().anyMatch(student ->
                student.getEmail().equals(addStudentRequestDto.getEmail())))
            throw new CustomException(ExceptionType.EMAIL_ALREADY_EXISTS, List.of(addStudentRequestDto.getEmail()));

        //System.out.println("nu am gasit studentii");
        //System.out.println("admin" + adminJPA.getReferenceById(adminId));
        Admin correspondingAdmin = adminJPA.findById(adminId)
                .orElseThrow(() -> new CustomException(ExceptionType.ADMIN_NOT_FOUND, List.of(adminId.toString())));

        //System.out.println("dormitory"+ dormitoryJPA.findById(correspondingAdmin.getDormitory().getDormitoryId()) );
        Dormitory dormitoryAdmin = dormitoryJPA.findById(correspondingAdmin.getDormitory().getDormitoryId())
                .orElseThrow(() -> new CustomException(ExceptionType.DORMITORY_NOT_FOUND, List.of(correspondingAdmin.getDormitory().getDormitoryId().toString())));

        //System.out.println(dormitoryAdmin);
        if (!dormitoryAdmin.getDormitoryId().equals(addStudentRequestDto.getDormitory()))
            throw  new CustomException(ExceptionType.STUDENT_DORMITORY_SHOULD_MATCH_ADMIN_DORMITORY, List.of(addStudentRequestDto.getDormitory().toString()));

        Student student = studentMapper.addStudentRequestDtoToStudent(dormitoryAdmin,addStudentRequestDto);
        Student stud = studentJPA.save(student);
        return studentMapper.studentToAddStudentResponseDto(stud);

    }

    @Override
    public UpdateStudentResponseDTO updateStudent(Integer studentId, UpdateStudentRequestDTO updateStudentRequestDTO) {
        Student studentToUpdate = studentJPA.findById(studentId).orElseThrow(() -> new CustomException(ExceptionType.STUDENT_NOT_FOUND, List.of(studentId.toString())));
        studentToUpdate.setFirstname(updateStudentRequestDTO.getFirstname());
        studentToUpdate.setLastname(updateStudentRequestDTO.getLastname());
        studentToUpdate.setBirthday(updateStudentRequestDTO.getBirthday());
        studentToUpdate.setEmail(updateStudentRequestDTO.getEmail());
        studentToUpdate.setAvatarImage(updateStudentRequestDTO.getAvatarImage());
        return studentMapper.studentToUpdateStudentResponseDto(studentJPA.save(studentToUpdate));
    }

    public static  List<Double> getOceanScores(UpdateStudentOceanRequestDTO updateStudentOceanRequestDTO)
    {
        CalculateOceanTraits calculateOceanTraits = new CalculateOceanTraits(
                updateStudentOceanRequestDTO.getOpenness_responses(),
                updateStudentOceanRequestDTO.getConscientiousness_responses(),
                updateStudentOceanRequestDTO.getExtroversion_responses(),
                updateStudentOceanRequestDTO.getAgreeableness_responses(),
                updateStudentOceanRequestDTO.getNeuroticism_responses()
        );
        return calculateOceanTraits.sumTraitsAndScale();

    }
    @Override
    public UpdateStudentOceanResponseDTO updateStudentOcean(Integer studentId, UpdateStudentOceanRequestDTO updateStudentOceanRequestDTO) {
        Student studentToUpdate = studentJPA.findById(studentId).orElseThrow(() -> new CustomException(ExceptionType.STUDENT_NOT_FOUND, List.of(studentId.toString())));
        List<Double> ocean_scores = getOceanScores(updateStudentOceanRequestDTO);
        studentToUpdate.setOpenness_score(ocean_scores.get(0));
        studentToUpdate.setConscientiousness_score(ocean_scores.get(1));
        studentToUpdate.setExtroversion_score(ocean_scores.get(2));
        studentToUpdate.setAgreeableness_score(ocean_scores.get(3));
        studentToUpdate.setNeuroticism_score(ocean_scores.get(4));
        return studentMapper.studentToUpdateStudentOceanResponseDto(studentJPA.save(studentToUpdate));
    }

    @Override
    public void updateStudentCluster(Integer studentId, ClusteringResponseDTO clusteringResponseDTO) {
        Student studentToUpdate = studentJPA.findById(studentId).orElseThrow(() -> new CustomException(ExceptionType.STUDENT_NOT_FOUND, List.of(studentId.toString())));
        studentToUpdate.setCluster(clusteringResponseDTO.getCluster());
        studentJPA.save(studentToUpdate);

    }


    @Override
    public GetStudentResponseDTO getStudentById(Integer studentId) throws CustomException {
        Optional<Student> student = studentJPA.findById(studentId);
        if (student.isEmpty()) {
            throw new CustomException(ExceptionType.ID_NOT_FOUND, List.of(studentId.toString()));
        }

        return studentMapper.studentToGetStudentResponseDto(student.get());
    }

    @Override
    public List<GetStudentResponseDTO> getStudents(Integer studentId) throws CustomException {
        Optional<Student> student = studentJPA.findById(studentId);
        if (student.isEmpty()) {
            throw new CustomException(ExceptionType.ID_NOT_FOUND, List.of(studentId.toString()));
        }
        Dormitory studentDormitory = student.get().getDormitory();
        List<Student> students = studentJPA.findStudentsWithSameDormitoryDifferentThanSelf(studentDormitory,studentId);
        if (students.isEmpty()) {
            throw new CustomException(ExceptionType.NO_STUDENT_FOUND);
        }

        return studentMapper.studentsToGetStudentResponseDtoList(students);
    }

    @Override
    public List<GetStudentResponseDTO> getStudentsByCluster(Integer id) throws CustomException {
        Student currentStudent = studentJPA.findById(id)
                .orElseThrow(() -> new CustomException(ExceptionType.STUDENT_NOT_FOUND, List.of(id.toString())));

        List<Student> students = studentJPA.findAll();
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students)
        {
            if (student.getDormitory().getDormitoryId().equals(currentStudent.getDormitory().getDormitoryId())){
               if (student.getCluster() != null && student.getCluster().equals(currentStudent.getCluster())) {
                   if (!student.getId().equals(currentStudent.getId()))
                      filteredStudents.add(student);
               }
             }
        }

        if (filteredStudents.isEmpty()){
            throw new CustomException(ExceptionType.STUDENTS_WITH_SAME_CLUSTER_AND_DORMITORY_NOT_FOUND,List.of(id.toString()));
        }

        return studentMapper.studentListToGetStudentResponseDtoList(filteredStudents);

    }

    @Override
    public void deleteStudentById(Integer studentId) {

    }
}
