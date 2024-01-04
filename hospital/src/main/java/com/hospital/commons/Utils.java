package com.hospital.commons;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Utils {

    private final HttpServletRequest request;

    public boolean isMobile(){
        //요청헤더 : User-Agent 패턴을 가지고 모바일인지 아닌지 체크
        //getHeader를 통해가져온다
        String ua = request.getHeader("User-Agent");

        //패턴
        String pattern = ".*(iPhone|iPod|iPad|BlackBerry|Android|Windows CE|LG|MOT|SAMSUNG|SonyEricsson).*";

        //위 패턴이있으면 모바일로 인지
        boolean isMobile = ua.matches(pattern);

        return isMobile;

    }

    public String tpl(String path){
        String prefix = isMobile() ? "mobile/" : "front/";

        return prefix + path;
    }
}
