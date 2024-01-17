package com.hospital.member.entities;

import com.hospital.commons.entities.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class Member extends Base{

    //기본키는 Wrapper class타입으로 입력
    @Id @GeneratedValue //자동증감 추가
    private Long seq;

    //이메일 & 아이디는 중복 X :  unique = true
    @Column(length = 80, nullable = false, unique = true)
    private String email;

    @Column(length = 40, nullable = false, unique = true)
    private String userId;

    @Column(length = 65, nullable = false)
    private String password;

    @Column(length = 40, nullable = false)
    private String name;
}
