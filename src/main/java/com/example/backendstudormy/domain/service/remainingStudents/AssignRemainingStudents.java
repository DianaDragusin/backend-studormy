package com.example.backendstudormy.domain.service.remainingStudents;

import com.example.backendstudormy.domain.dto.clustering.BigFiveResponses;
import com.example.backendstudormy.domain.dto.clustering.ClusteringRequestDTO;
import com.example.backendstudormy.domain.dto.clustering.ClusteringResponseDTO;
import com.example.backendstudormy.domain.dto.group.GroupResponseDTO;
import com.example.backendstudormy.domain.dto.group.addGroup.AddStudentToGroupRequestDTO;
import com.example.backendstudormy.domain.dto.room.getRoom.GetRoomResponseDTO;
import com.example.backendstudormy.domain.dto.student.getStudent.GetStudentResponseDTO;
import com.example.backendstudormy.domain.entities.Dormitory;
import com.example.backendstudormy.domain.entities.Group;
import com.example.backendstudormy.domain.entities.Room;
import com.example.backendstudormy.domain.entities.Student;
import com.example.backendstudormy.domain.exceptions.CustomException;
import com.example.backendstudormy.domain.exceptions.ExceptionType;
import com.example.backendstudormy.domain.mapper.IGroupMapper;
import com.example.backendstudormy.domain.mapper.IRoomMapper;
import com.example.backendstudormy.domain.mapper.IStudentMapper;
import com.example.backendstudormy.domain.repository.*;
import com.example.backendstudormy.domain.service.KendallTauDistance;
import com.example.backendstudormy.domain.service.group.IGroupService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AssignRemainingStudents implements  IAssignRemainingStudents{
    private IGroupService groupService;
    private IGroupMapper groupMapper;
    private IAdminJPA adminJPA;
    private IGroupJPA groupJPA;
    private IRoomJPA roomJPA;
    private IStudentJPA studentJPA;
    private IStudentMapper studentMapper;
    private IRoomMapper roomMapper;
    private IDormitoryJPA dormitoryJPA;
    private List<Student> studentsNoCluster;
    public static Integer numberStudents;

    // get the first n students (n = room capacity) that are the most compatible
    private List<Integer> getCompatibleStudents(List<Integer> studentRanking,Map<Integer, List<Integer>> restStudents, int capacity){
        List<Integer> compatibleStudents = new ArrayList<>();
        Map<Integer, Double> scores = new HashMap<>();

        for (Map.Entry<Integer, List<Integer>> entry : restStudents.entrySet()) {
            Integer currentStudentId = entry.getKey();
            List<Integer> currentStudentRanking = entry.getValue();
            double score = KendallTauDistance.calculateKendallTau(studentRanking, currentStudentRanking);
            scores.put(currentStudentId, score);
        }
        List<Map.Entry<Integer, Double>> sortedEntries = new ArrayList<>(scores.entrySet());
        sortedEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (int i = 0; i < capacity && i < sortedEntries.size(); i++) {
            compatibleStudents.add(sortedEntries.get(i).getKey());
        }

        return compatibleStudents;
    }
    private void addStudentToGroup( GroupResponseDTO groupResponseDTO, Integer studentId){
        AddStudentToGroupRequestDTO addStudentToGroupRequestDTO = new AddStudentToGroupRequestDTO();
        addStudentToGroupRequestDTO.setGroupId(groupResponseDTO.getGroupId());
        addStudentToGroupRequestDTO.setNewMemberId(studentId);
        this.groupService.addStudentToGroup(addStudentToGroupRequestDTO);

    }
    private static Map<Integer, List<Integer>> getRankings(Map<Integer, List<Integer>> rankedClustersOfStudents, int howManyToRemove) {
        // Create a shallow copy of the original map
        Map<Integer, List<Integer>> copyMap = new HashMap<>(rankedClustersOfStudents);

        // Convert key set to a list to access elements by index
        List<Integer> keys = new ArrayList<>(rankedClustersOfStudents.keySet());

        // Remove the specified number of entries
        for (int i = 0; i < howManyToRemove && !keys.isEmpty(); i++) {
            copyMap.remove(keys.get(i));
        }

        return copyMap;
    }

    @Override
    public List<GroupResponseDTO> moveRemainingStudentsToVacantRooms(Integer dormitoryId,Map<Integer, List<Integer>> rankedClustersOfStudents) throws CustomException {
        try{
            int index = 0;
            List<Room> rooms = this.getUnassignedRooms(dormitoryId );
            for(int roomindex = 0 ; roomindex < rooms.size(); roomindex ++){
                if(!getRankings(rankedClustersOfStudents,index).isEmpty()){
                    index ++;
                    Integer[] keys = rankedClustersOfStudents.keySet().toArray(new Integer[0]);
                    Integer firstKey = keys[index - 1];
                    List<Integer> rankingFirst = rankedClustersOfStudents.get(firstKey);

                    List<Integer> compStudentsId = getCompatibleStudents(rankingFirst,getRankings(rankedClustersOfStudents,index),rooms.get(roomindex).getMaxPeopleNumber() -1 );

                    GroupResponseDTO groupResponseDTO = this.groupService.addGroup(firstKey,"");

                    addStudentToGroup(groupResponseDTO,firstKey);

                    for (int studIndex = 0 ; studIndex < compStudentsId.size(); studIndex ++){

                        addStudentToGroup(groupResponseDTO,compStudentsId.get(studIndex));
                    }
                    this.groupService.applyForARoomWithAGroup(groupResponseDTO.getGroupId(),rooms.get(roomindex).getRoomId());

                }else if (!this.studentsNoCluster.isEmpty()){
                    Student studentGroupInitiator = this.studentsNoCluster.get(0);
                    this.studentsNoCluster.remove(studentGroupInitiator);
                    GroupResponseDTO groupResponseDTO = this.groupService.addGroup(studentGroupInitiator.getId(),"");
                    addStudentToGroup(groupResponseDTO,studentGroupInitiator.getId());

                    for (int i = 0; i < rooms.get(roomindex).getMaxPeopleNumber() - 1; i ++)
                    {
                        if (!this.studentsNoCluster.isEmpty())
                        {
                            Student studentToAddToGroup = this.studentsNoCluster.get(0);
                            this.studentsNoCluster.remove(studentToAddToGroup);
                            addStudentToGroup(groupResponseDTO,studentToAddToGroup.getId());

                        }
                        else{
                            break;
                        }
                    }
                    this.groupService.applyForARoomWithAGroup(groupResponseDTO.getGroupId(),rooms.get(roomindex).getRoomId());
                }
            }
            return this.groupService.getGroupsAssignedToARoom(dormitoryId);
        }catch (Exception e){
            throw new CustomException(ExceptionType.ERROR_ASSIGNING_STUDENTS);
        }

    }


    @Override
    public List<BigFiveResponses> getStudentsWithNoRoom(Integer dormitoryId) throws CustomException {
        List<Student> students = studentJPA.findAll().stream().filter(stud-> stud.getDormitory().getDormitoryId().equals(dormitoryId)).toList();
        List<BigFiveResponses> studentsNotAssigned = new ArrayList<>();
        if (students.isEmpty()) {
            throw new CustomException(ExceptionType.NO_STUDENT_FOUND);
        }
        else {
            for (Student student : students){
                boolean hasRoomAssigned = false;
                Set<Group> groups = student.getGroups();
                for (Group group : groups)
                {
                    if (group.getRoom() != null) {
                        hasRoomAssigned = true;
                        break;
                    }
                }
                if (!hasRoomAssigned)
                {
                    if (student.getCluster() != null)
                        studentsNotAssigned.add(studentMapper.studentToBigFiveResponses(student));
                    else{
                        this.studentsNoCluster.add(student);
                    }

                }

            }

        }
        return studentsNotAssigned;
    }
    @Override
    public BigFiveResponses getFiveScores(Integer studentId) {
        Student studentFound = studentJPA.findById(studentId).orElseThrow(() -> new CustomException(ExceptionType.STUDENT_NOT_FOUND, List.of(studentId.toString())));
        return  studentMapper.studentToBigFiveResponses(studentFound);
    }
    @Override
    public List<Room> getAllRooms(Integer dormitoryId) throws CustomException {
        Optional<Dormitory> dormitory = dormitoryJPA.findById(dormitoryId);
        if (dormitory.isEmpty()) {
            throw new CustomException(ExceptionType.ID_NOT_FOUND, List.of(dormitoryId.toString()));
        }
        List<Room> rooms =dormitory.get().getRooms();
        if (rooms.isEmpty())
        {
            throw new CustomException(ExceptionType.NO_ROOM_FOUND);
        }

        return rooms;
    }



    @Override
    public List<Room> getUnassignedRooms(Integer dormitoryId) throws CustomException {
        List<Group> groups = groupJPA.findAll();
        List<Room> rooms = getAllRooms(dormitoryId);
        if (groups.isEmpty())
        {
            throw  new CustomException(ExceptionType.GROUP_NOT_FOUND);
        }
        if (rooms.isEmpty())
        {
            throw  new CustomException(ExceptionType.NO_ROOM_FOUND);
        }
        for(Group group : groups){
            if (group.getRoom() != null) {
                rooms.removeIf(room -> room.getRoomId().equals(group.getRoom().getRoomId()));
            }
        }
        rooms.sort(Comparator.comparingInt(Room::getMaxPeopleNumber));
        return  rooms;
    }

}
