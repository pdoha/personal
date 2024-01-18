package com.hospital.member.service;

import com.hospital.member.controllers.JoinValidator;
import com.hospital.member.controllers.RequestJoin;
import com.hospital.member.entities.Member;
import com.hospital.member.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final MemberRepository memberRepository; //회원가입 성공시 DB저장
    private final JoinValidator validator; //검증추가
    private final PasswordEncoder encoder; //비밀번호 해시화


    //회원가입 처리
    //커맨드 객체 형태로 가입들어올 경우도 있음

    public void process(RequestJoin form, Errors errors){
        validator.validate(form, errors); //검증하고

        //실패하면 로직을 수행하지 않고 return 으로 종료
        if(errors.hasErrors()){
            return;
        }

        //검증 성공시
        //비밀번호 BCrypt로 해시화
        String hash = encoder.encode(form.getPassword());
        //커맨드 객체 form 을 변환메소드 map이 멤버 정보를 비교해서 해시화된 비번만 넣어줌 (ModelMapper의존성 이용해도가능)
        //잠시 오류때문에 settet썼음
        //회원가입시 회원정보
        //회원가입 회원정보
        Member member = new Member();
        member.setEmail(form.getEmail());
        member.setName(form.getName());
        member.setPassword(hash); //비밀번호 해시화
        member.setUserId(form.getUserId());

        //DB에 저장
        process(member);


    }

    public void process(Member member){
        memberRepository.saveAndFlush(member);
    }
}
