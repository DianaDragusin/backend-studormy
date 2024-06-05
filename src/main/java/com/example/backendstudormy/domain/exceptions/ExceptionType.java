package com.example.backendstudormy.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ExceptionType {
    ID_INVALID(HttpStatus.BAD_REQUEST, "Id %s is invalid. Id must be a number.", 4001),
    ID_NOT_FOUND(HttpStatus.NOT_FOUND, "Id %s does not exist.", 4002),
    ID_EMPTY(HttpStatus.BAD_REQUEST, "Id cannot be empty.", 4003),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "Email %s already exists.", 4004),
    NAME_EMPTY(HttpStatus.BAD_REQUEST, "Name cannot be empty.", 4005),
    NAME_TOO_LONG(HttpStatus.BAD_REQUEST, "Name is too long.", 4006),
    DORMITORY_NOT_FOUND(HttpStatus.NOT_FOUND, "Dormitory %s does not exist.", 4007),
    DORMITORY_ALREADY_EXISTS(HttpStatus.CONFLICT, "Dormitory %s already exists.", 4008),
    DORMITORY_EMPTY(HttpStatus.BAD_REQUEST, "Dormitory cannot be empty.", 4009),
    DATE_RETURNED_IS_BEFORE_DATE_GIVEN(HttpStatus.CONFLICT, "Date returned cannot be earlier than date given.", 4010),
    ADMIN_NOT_FOUND(HttpStatus.NOT_FOUND, "Admin %s does not exist.", 4011),
    STUDENT_DORMITORY_SHOULD_MATCH_ADMIN_DORMITORY(HttpStatus.BAD_REQUEST,"Admin with a dormitory cannot add students to foreign dormitories .",4012),
    MULTIPLE_ERRORS_UPLOAD(HttpStatus.CONFLICT,"%s could not be added to the database .",4013),
    AUTHENTICATION_REQUIRED(HttpStatus.UNAUTHORIZED, "Full authentication is required to access this resource.", 3001),
    NO_PERMISSION(HttpStatus.FORBIDDEN, "Access denied.", 3002),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error", 5001),
    CREDENTIAL_ERROR(HttpStatus.CONFLICT, "Credentials incorrect", 3003),
    STUDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Student %s does not exist.", 4014),
    STUDENTS_WITH_SAME_CLUSTER_AND_DORMITORY_NOT_FOUND(HttpStatus.NOT_FOUND, "No student has the same dormitory and cluster", 4015),
    DUPLICATE_GROUP_NAMES(HttpStatus.BAD_REQUEST, "There is already a group that has the same name as yours", 4016),
    GROUP_NOT_FOUND(HttpStatus.NOT_FOUND, "No group with this name found", 4017),
    GROUP_WITH_MORE_THAN_ONE_MEMBER(HttpStatus.BAD_REQUEST, "Group with more than one student can't be deleted", 4018),
    STUDENT_CAN_NOT_BE_REMOVED_FROM_GROUP(HttpStatus.BAD_REQUEST, "Student can't be removed from this group", 4019),
    NO_STUDENT_FOUND(HttpStatus.NOT_FOUND, "No student was found in the database", 4020),
    NO_ROOM_FOUND(HttpStatus.NOT_FOUND, "No room was found in the database", 4021),
    STOP_BEFORE_START(HttpStatus.BAD_REQUEST, "Can't stop the room allocation process before initialization", 4022),
    GROUP_HAS_ALREADY_ROOM(HttpStatus.BAD_REQUEST, "Permission denied to apply for a second room", 4023),
    ERROR_ASSIGNING_STUDENTS(HttpStatus.BAD_REQUEST, "There has been an error while assigning students to vacant rooms", 4024),
    DORMITORY_NAME_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "There is already a dormitory with this name", 4025),
    NO_CLUSTER(HttpStatus.BAD_REQUEST, "Student did not take personality test", 4026);











    private final HttpStatus httpStatus;
    private final String errorMessage;
    private final Integer businessCode;
}
