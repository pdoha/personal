package com.hospital.commons.exceptions;

import org.springframework.http.HttpStatus;

public class CommonException extends RuntimeException {
    private HttpStatus status;

    public CommonException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    //상태코드 조회
    public HttpStatus getStatus(){
        return status;
    }
}