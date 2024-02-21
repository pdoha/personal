package com.hospital.commons;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class Utils {

    private final HttpServletRequest request;
    private final HttpSession session;

    //메세지 번들
    //객체를 만들지 않아도 클래스가 로드될때 실행되게 하기 위해서 static 사용
    private static final ResourceBundle commonsBundle;
    private static final ResourceBundle validationsBundle;
    private static final ResourceBundle errorsBundle;

    //메세지 코드에 대한 번들 가져오기
    //상황에 따라 맞는 다른 메세지를 가져올 수 있다
    static{
        commonsBundle = ResourceBundle.getBundle("messages.commons");
        validationsBundle = ResourceBundle.getBundle("messages.validations");
        errorsBundle = ResourceBundle.getBundle("messages.errors");
    }

    public boolean isMobile(){
        //모바일 수동 전환 모드 체크
        String device = (String) session.getAttribute("device");
        if(StringUtils.hasText(device)){
            return device.equals("MOBILE");
        }
        //요청헤더 : User-Agent 패턴을 가지고 모바일인지 아닌지 체크
        //getHeader를 통해가져온다
        String ua = request.getHeader("User-Agent");

        //패턴
        String pattern = ".*(iPhone|iPod|iPad|BlackBerry|Android|Windows CE|LG|MOT|SAMSUNG|SonyEricsson).*";

        //위 패턴이있으면 모바일로 인지
        return ua.matches(pattern);



    }

    public String tpl(String path){
        String prefix = isMobile() ? "mobile/" : "front/";

        return prefix + path;
    }

    //메세지 번들 null값에 대한 검증
    //있으면 타입을 그대로 넣고 없으면  validations;
    public static String getMessage(String code, String type){
        type = StringUtils.hasText(type) ? type : "validations";

        ResourceBundle bundle = null;
        if( type.equals("commons")){
            bundle = commonsBundle;
        } else if(type.equals("errors")){
            bundle = errorsBundle;
        } else{
            bundle = validationsBundle;
        }

        //키워드( 키값) 은 코드로 조회
        return bundle.getString(code);
    }

    //유효성 검사에 있는거 가져오기
    public static String getMessage(String code){
        return getMessage(code, null);
    }

    //줄개행 문자가 있으면 br태그로 바꿔주는 편의기능
    // -> \n 또는 \r\n -> <br>
    public String nl2br(String str){
        str = str.replaceAll("\\n", "<br>")
                .replaceAll("\\r", "");
        return str;

    }



}
