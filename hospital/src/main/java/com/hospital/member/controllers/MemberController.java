package com.hospital.member.controllers;


import com.hospital.commons.ExceptionProcessor;
import com.hospital.commons.Utils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member") //기본주소
@RequiredArgsConstructor //자동의존성 주입
public class MemberController implements ExceptionProcessor { //발생한 에러 유입될 수 있게
    //의존성
    private final Utils utils;
    private final JoinValidator joinValidator;

    //회원가입
    @GetMapping("/join")
    public String join(@ModelAttribute RequestJoin form){ //커맨드 객체 연동
                                                          //값이없어도 접근할 수있게 modelAttribute

        //템플릿 연동
        return utils.tpl("member/join");
    }
    //회원가입처리
    @PostMapping("/join")
    public String joinPs(@Valid RequestJoin form, Errors errors){ //커맨드 검증 연동 @Valid
                                                                      //검증 실패시 errors 객체에 담김
        joinValidator.validate(form, errors);
        //hasErrors가 참이면 입력한 값을 보여준다 (수정할수있게)
        if(errors.hasErrors()){
            return utils.tpl("member/join");
        }
        //회원가입 완료되면 로그인페이지로 이동
        return "redirect:/member/login";

    }
}
