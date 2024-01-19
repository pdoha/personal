package com.hospital.admin.controllers;

import com.hospital.commons.ExceptionProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component("adminMainController")
@RequestMapping("/admin")
public class MainController implements ExceptionProcessor {


    //메인페이지
    @GetMapping
    public  String index(){
        return "admin/main/index";
    }
}
