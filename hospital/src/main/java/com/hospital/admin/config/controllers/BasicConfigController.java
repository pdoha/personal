package com.hospital.admin.config.controllers;

import com.hospital.commons.ExceptionProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/config")
public class BasicConfigController implements ExceptionProcessor {

    //주메뉴
    @ModelAttribute("menuCode")
    public String getMenuCode(){
        return "config";
    }
    //서브메뉴
    @ModelAttribute("subMenuCode")
    public String getSubMenuCode(){
        return "basic";
    }

    //기본설정 메인 페이지
    @GetMapping
    public String index(@ModelAttribute BasicConfig config, Model model){
        return "admin/config/basic";
    }
    //저장하면 동일한 템플릿을 보여준다
    // //-> 그래서 메인페이지랑 주소가 동일
    @PostMapping
    public String save(BasicConfig config, Model model){
        return "admin/config/basic";
    }

}
