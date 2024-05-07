package com.example.backendstudormy.domain.dto.group.addGroup;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class AddStudentToGroupRequestDTO {
    private Integer groupId;
    private Integer newMemberId;
}
