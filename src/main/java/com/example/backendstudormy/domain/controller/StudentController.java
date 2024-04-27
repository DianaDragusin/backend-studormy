package com.example.backendstudormy.domain.controller;
import com.example.backendstudormy.domain.dto.clustering.ClusteringRequestDTO;
import com.example.backendstudormy.domain.dto.clustering.ClusteringResponseDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentOceanRequestDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentOceanResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentResponseDTO;
import com.example.backendstudormy.domain.service.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/student")
public class StudentController {
    private IStudentService studentService;
    private final RestTemplate restTemplate;

    @PostMapping("/uploadStudents/{id}")
    public List<AddStudentResponseDTO> uploadStudents(@PathVariable Integer id,@RequestBody List<AddStudentRequestDTO> uploadStudentsRequestDTOList) {
        return studentService.uploadStudents(id,uploadStudentsRequestDTOList);
    }

    @GetMapping("{id}")
    public GetStudentResponseDTO getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }
    @GetMapping("/cluster/{id}")
    public List<GetStudentResponseDTO> getStudentsByCluster(@PathVariable Integer id) {
        return studentService.getStudentsByCluster(id);
    }

    @PutMapping("/ocean/{id}")
    public UpdateStudentOceanResponseDTO updateStudentOceanScores(@PathVariable Integer id,@RequestBody UpdateStudentOceanRequestDTO updateStudentOceanRequestDto) {
        return studentService.updateStudentOcean(id,updateStudentOceanRequestDto);
    }
    @PutMapping("/update/{id}")
    public UpdateStudentResponseDTO updateStudentOceanScores(@PathVariable Integer id,@RequestBody UpdateStudentRequestDTO updateStudentRequestDto) {
        return studentService.updateStudent(id,updateStudentRequestDto);
    }


    @PutMapping(value = "/personality-cluster-prediction/{id}")
    public ClusteringResponseDTO getClusterForAStudent(@PathVariable Integer id, @RequestBody ClusteringRequestDTO clusteringRequestDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<ClusteringRequestDTO> entity = new HttpEntity<>(clusteringRequestDTO, headers);
        String response = restTemplate.exchange("http://localhost:5000/personality-cluster-prediction", HttpMethod.POST, entity, String.class).getBody();
        if (response != null)
        {
            response = response.trim();
            Integer cluster = Integer.parseInt(response);
            ClusteringResponseDTO clusteringResponseDTO = new ClusteringResponseDTO();
            clusteringResponseDTO.setCluster(cluster);
            System.out.println(cluster);
            studentService.updateStudentCluster(id,clusteringResponseDTO);
            return clusteringResponseDTO;
        }
        return null;


    }
}
