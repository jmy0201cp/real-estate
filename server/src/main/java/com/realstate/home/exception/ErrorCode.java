package com.realstate.home.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DO_NOT_FOUND(HttpStatus.NOT_FOUND),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND),
    PASSWORD_IS_NOT_VALID(HttpStatus.CONFLICT),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    DUPLICATED_DATA(HttpStatus.CONFLICT),
    DO_NOT_MATCH(HttpStatus.CONFLICT);

    private HttpStatus status;
}
