package com.example.backendstudormy.domain.controller;

import com.example.backendstudormy.domain.dto.clustering.BigFiveResponses;
import com.example.backendstudormy.domain.dto.clustering.ClusteringRequestDTO;
import com.example.backendstudormy.domain.dto.clustering.ClusteringResponseDTO;
import com.example.backendstudormy.domain.dto.group.GroupResponseDTO;
import com.example.backendstudormy.domain.mapper.IStudentMapper;
import com.example.backendstudormy.domain.service.remainingStudents.IAssignRemainingStudents;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/assignRemainingStudents")
public class AssignRemainingStudentsController {
    private IAssignRemainingStudents assignRemainingStudents;
    private final RestTemplate restTemplate;
    private IStudentMapper studentMapper;
    @PutMapping(value = "/ranking_clusters_for_student/")
    public List<GroupResponseDTO> mapRemainingStudentsToRemainingRooms(@RequestBody Integer dormitoryId) {
        List<BigFiveResponses> studentsWithNoRoom = assignRemainingStudents.getStudentsWithNoRoom(dormitoryId);
        Map<Integer,List<Integer>> studentsRankingClusters = new HashMap<>() ;
        for(BigFiveResponses student : studentsWithNoRoom)
        {
            ClusteringRequestDTO clusteringRequestDTO = studentMapper.BigFiveResponsesToClusteringRequestDTO(student);
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            HttpEntity<ClusteringRequestDTO> entity = new HttpEntity<>(clusteringRequestDTO, headers);
            String response = restTemplate.exchange("http://localhost:5000/ranking", HttpMethod.POST, entity, String.class).getBody();
            if (response != null)
            {
                response = response.trim();
                //no []
                String cleanedResponse = response.substring(1, response.length() - 1);

                // Split the string by commas
                List<Integer> rankedCluster = Stream.of(cleanedResponse.split(",")).map(Integer::parseInt).toList();
                studentsRankingClusters.put(student.getId(),rankedCluster) ;

            }
        }
       return assignRemainingStudents.moveRemainingStudentsToVacantRooms(dormitoryId,studentsRankingClusters);

    }
}
