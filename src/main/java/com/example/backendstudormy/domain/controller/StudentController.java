package com.example.backendstudormy.domain.controller;
import com.example.backendstudormy.domain.dto.Membership.GetMembershipValuesDTO;
import com.example.backendstudormy.domain.dto.Membership.GetStudentMembershipValues;
import com.example.backendstudormy.domain.dto.clustering.BigFiveResponses;
import com.example.backendstudormy.domain.dto.clustering.ClusteringRequestDTO;
import com.example.backendstudormy.domain.dto.clustering.ClusteringResponseDTO;
import com.example.backendstudormy.domain.dto.lessInfoStudent.LessInfoStudent;
import com.example.backendstudormy.domain.dto.room.addRoom.AddRoomResponseDTO;
import com.example.backendstudormy.domain.dto.room.getRoom.GetRoomResponseDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.addStudent.AddStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentOceanRequestDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentOceanResponseDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentRequestDTO;
import com.example.backendstudormy.domain.dto.student.updateStudent.UpdateStudentResponseDTO;
import com.example.backendstudormy.domain.mapper.IStudentMapper;
import com.example.backendstudormy.domain.service.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/student")
public class StudentController {
    private IStudentService studentService;
    private IStudentMapper studentMapper;
    private final RestTemplate restTemplate;

    @PostMapping("/uploadStudents/{id}")
    public List<AddStudentResponseDTO> uploadStudents(@PathVariable Integer id,@RequestBody List<AddStudentRequestDTO> uploadStudentsRequestDTOList) {
        return studentService.uploadStudents(id,uploadStudentsRequestDTOList);
    }

    @GetMapping("{id}")
    public GetStudentResponseDTO getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }
    @GetMapping("lessInfoStudent/{id}")
    public LessInfoStudent getStudentLessInfoById(@PathVariable Integer id) {
        return studentService.getStudentLessInfoStudentById(id);
    }

    @GetMapping("hasRoom/{id}")
    public Boolean getStudentHasRoom(@PathVariable Integer id) {
        return studentService.getStudentHasRoom(id);
    }

    @GetMapping("/no/{id}")
    public List<GetStudentResponseDTO> getStudents(@PathVariable Integer id) {
        return studentService.getStudents(id);
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
    private GetMembershipValuesDTO initializeGetMembershipValuesDTO( List<Double> membershipCluster)
    {
        GetMembershipValuesDTO getMembershipValuesDTO = new GetMembershipValuesDTO();

        getMembershipValuesDTO.setCluster0(membershipCluster.get(0));
        getMembershipValuesDTO.setCluster1(membershipCluster.get(1));
        getMembershipValuesDTO.setCluster2(membershipCluster.get(2));
        getMembershipValuesDTO.setCluster3(membershipCluster.get(3));
        getMembershipValuesDTO.setCluster4(membershipCluster.get(4));
        return getMembershipValuesDTO;

    }
    private GetStudentMembershipValues initializeGetStudentMembershipValuesDTO( List<Double> membershipCluster, GetStudentResponseDTO getStudentResponseDTO)
    {
        GetStudentMembershipValues getStudentMembershipValuesDTO = new GetStudentMembershipValues();

        getStudentMembershipValuesDTO.setEmail(getStudentResponseDTO.getEmail());
        getStudentMembershipValuesDTO.setCluster0(membershipCluster.get(0));
        getStudentMembershipValuesDTO.setCluster1(membershipCluster.get(1));
        getStudentMembershipValuesDTO.setCluster2(membershipCluster.get(2));
        getStudentMembershipValuesDTO.setCluster3(membershipCluster.get(3));
        getStudentMembershipValuesDTO.setCluster4(membershipCluster.get(4));
        return getStudentMembershipValuesDTO;

    }
    @GetMapping(value = "/membership_values/{id}")
    public GetMembershipValuesDTO getMembershipValues(@PathVariable Integer id) {
        ClusteringRequestDTO clusteringRequestDTO = studentService.getScoresStudentById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<ClusteringRequestDTO> entity = new HttpEntity<>(clusteringRequestDTO, headers);
        String response = restTemplate.exchange("http://localhost:5000/membership-cluster", HttpMethod.POST, entity, String.class).getBody();
        if (response != null)
        {
            response = response.trim();
            //no []
            String cleanedResponse = response.substring(1, response.length() - 1);

            // Split the string by commas
            List<Double> membershipCluster = Stream.of(cleanedResponse.split(",")).map(Double::parseDouble).toList();

           return initializeGetMembershipValuesDTO(membershipCluster);
        }
       return null;
    }

    @GetMapping(value = "roommates/membership_values/{id}")
    public List<GetStudentMembershipValues> getRoommatesMembershipValues(@PathVariable Integer id) {
        List<GetStudentMembershipValues> getStudentMembershipValuesList = new ArrayList<>();
        List<GetStudentResponseDTO> roommatesData = studentService.getRoommatesOfStudent(id);
        for (GetStudentResponseDTO roommateData : roommatesData){
            ClusteringRequestDTO clusteringRequestDTO = studentMapper.getStudentResponseDtoToClusteringRequest(roommateData);
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            HttpEntity<ClusteringRequestDTO> entity = new HttpEntity<>(clusteringRequestDTO, headers);
            String response = restTemplate.exchange("http://localhost:5000/membership-cluster", HttpMethod.POST, entity, String.class).getBody();
            if (response != null)
            {
                response = response.trim();
                String cleanedResponse = response.substring(1, response.length() - 1);
                List<Double> membershipCluster = Stream.of(cleanedResponse.split(",")).map(Double::parseDouble).toList();

                getStudentMembershipValuesList.add(initializeGetStudentMembershipValuesDTO(membershipCluster,roommateData));
            }
        }


        return getStudentMembershipValuesList;
    }
    @GetMapping(value = "roommatesIds/{id}")
    public List<Integer> getRoommatesIds(@PathVariable Integer id) {
        return studentService.getRoommatesIds(id);
    }
    @GetMapping(value = "roommates/{id}")
    public List<LessInfoStudent> getRoommates(@PathVariable Integer id) {
        return studentService.getRoommates(id);
    }
    @GetMapping(value = "roommatesOnly/{id}")
    public List<LessInfoStudent> getRoommatesOnly(@PathVariable Integer id) {
        return studentService.getRoommatesOnly(id);
    }
    @GetMapping(value = "room/{id}")
    public AddRoomResponseDTO getRoom(@PathVariable Integer id) {

        return studentService.getRoomOfStudent(id);
    }
}
