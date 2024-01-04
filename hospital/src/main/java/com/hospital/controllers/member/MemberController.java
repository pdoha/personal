package com.hospital.controllers.member;


import com.hospital.commons.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    //의존성
    private final Utils utils;

    @GetMapping("/join")
    //회원가입
    public String join(){

        //템플릿 연동
        return utils.tpl("member/join");

    }
}
