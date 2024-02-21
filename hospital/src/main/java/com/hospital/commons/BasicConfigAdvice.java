package com.hospital.commons;

import com.hospital.admin.config.controllers.BasicConfig;
import com.hospital.admin.config.service.ConfigInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice("com.hospital") //전체범위에 설정값 유지
@RequiredArgsConstructor
public class BasicConfigAdvice {
    //컨트롤러 실행전에 공통 부분 전역에 설정값 유지
    private final ConfigInfoService infoService;

    @ModelAttribute("siteConfig")
    public BasicConfig getBasicConfig(){

        BasicConfig config = infoService.get("basic", BasicConfig.class).orElseGet(BasicConfig::new);
        return config;
    }

    public String get(String key){
        return null;
    }
}
