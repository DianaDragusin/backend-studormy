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
    STUDENTS_WITH_SAME_CLUSTER_AND_DORMITORY_NOT_FOUND(HttpStatus.NOT_FOUND, "No student has the same dormitory and cluster", 4015);



    private final HttpStatus httpStatus;
    private final String errorMessage;
    private final Integer businessCode;
}
