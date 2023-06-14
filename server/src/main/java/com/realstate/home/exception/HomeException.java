package com.realstate.home.exception;

import lombok.AllArgsConstructor;

//TO DO
@AllArgsConstructor
public class HomeException extends RuntimeException {

    private ErrorCode errorCode;


    public HomeException() {
        this.errorCode = null;
    }
}
