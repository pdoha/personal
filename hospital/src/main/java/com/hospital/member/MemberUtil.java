package com.hospital.member;

import com.hospital.member.entities.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUtil {
    //session으로 회원 조회하기
    private final HttpSession session;

    //로그인 여부 확인 (세션데이터가 있으면 로그인한 상태)
    public boolean isLogin(){
        return getMember() != null;
    }

    //회원정보를 세션쪽으로 가져옴
    //회원 데이터 조회
    //세션에 저장된 멤버데이터를 가져오는 것
    public Member getMember(){
        Member member = (Member) session.getAttribute("member");

        return member;
    }

    //로그인 세션 비우기
    public static void clearLoginData(HttpSession session){
        session.removeAttribute("userId");
        session.removeAttribute("NotBlank_username");
        session.removeAttribute("NotBlank_password");
        session.removeAttribute("Global_error");

    }
}
