package com.hospital.admin.config.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hospital.admin.config.entities.Configs;
import com.hospital.admin.config.repositories.ConfigsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigSaveService {
    private final ConfigsRepository repository;

    public <T> void save(String code, T data){ //매개변수에 코드 넣고, json 문자열로 바꿔주자
       //조회했을때 영속성안에 있으면 가져오고
        //없으면 새로운 엔티티 만든다
        Configs configs = repository.findById(code).orElseGet(Configs::new);

        ObjectMapper om = new ObjectMapper(); //데이터 치환
        om.registerModule(new JavaTimeModule()); //자바타임모듈 추가

        //반환값은 json 문자열
        try {
            String jsonString = om.writeValueAsString(data);
            //예외 발상하지 않은 구간에서 DB에 저장
            configs.setData(jsonString);
            //처음만드는 엔티티는 코드가 없으니까 넣어줘야함
            configs.setCode(code);

            //DB저장
            repository.saveAndFlush(configs);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
