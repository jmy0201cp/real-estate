package com.realstate.home.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

//TO DO
@Getter
@AllArgsConstructor
public class HomeException extends RuntimeException {

    private ErrorCode errorCode;


    public HomeException() {
        this.errorCode = null;
    }
}
