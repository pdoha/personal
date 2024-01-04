package com.hospital.controllers;

import com.hospital.commons.exceptions.CommonException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.hospital.controllers")
public class CommonController {
    //모든예외가 발생하면 여기로옴
    @ExceptionHandler(Exception.class)
    public String errorHandler(Exception e, HttpServletResponse response, Model model){

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; //500으로 고정

        //e가 CommonException의 예외이면
        if(e instanceof CommonException){
            CommonException commonException = (CommonException) e;
            status = commonException.getStatus();
        }

        response.setStatus(status.value());

        e.printStackTrace();
        //공통 예외 템플릿 연결
        return "error/common";
    }
}
