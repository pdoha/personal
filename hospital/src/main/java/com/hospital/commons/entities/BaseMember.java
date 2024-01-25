package com.hospital.commons.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter @Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) //이벤트 감지
public abstract class BaseMember extends Base{
    //날짜 시간 정보 + 회원정보가 들어가있는 공통 속성

    @CreatedBy //회원정보가 자동으로 주입
    @Column(length = 40, updatable = false) //수정 x
    private String createdBy; //처음 추가한 사용자

    @LastModifiedBy
    @Column(length = 40, insertable = false) //추가 x
    private String modifiedBy; //데이터를 수정한 사용자 아이디



}
