package com.hospital.commons.interceptors;


import com.hospital.member.MemberUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CommonInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        checkDevice(request);

        //로그인페이지가 아닌 페이지일때는 메세지관련 세션내용을 비우자
        clearLoginData(request);

        return true;
    }

    /**
     * pc, mobile 수동 변경 처리
     *
     * url로 수동전환
     * ?device=pc  → pc 뷰
     * ?device=mobile → mobile 뷰
     *
     * @param request
     */
    //session가지고 등록
    private void checkDevice(HttpServletRequest request){
        //파라미터값 가져오기
        String device = request.getParameter("device");

        //값이 없을 경우
        if(!StringUtils.hasText(device)){
            return;
        }

        //값이 있을 경우
        //모바일 값이 아니면 -> pc
        //대소문자 구분 없음
        device = device.toUpperCase().equals("MOBILE") ? "MOBILE" : "PC";

        //session 가져와서 넣자
        HttpSession session = request.getSession();

        //값이 있으면 고정
        session.setAttribute("device", device);

    }

    //로그인페이지가 아닌 페이지일때는 메세지관련 세션내용을 비우자
    //->접속한 주소가 뭔지알아야함 getRequestURI
    private void clearLoginData(HttpServletRequest request){
        String URL = request.getRequestURI();
        //주소 url member/login 주소가 아닌 페이지면 전부 제거
        if(URL.indexOf("/member/login") == -1){  //문자열이 포함되지않을때  indexof
            //세션객체 가져와서 getSession
            HttpSession session = request.getSession();
            //만들어놓은  MemberUtil 이용 (공통처리를 위해 넣어준 인터셉터)
            MemberUtil.clearLoginData(session);
        }
    }
}
