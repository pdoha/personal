package com.hospital.member.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.hospital.commons.entities.Base;
@Data
@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor //기본생성자 추가
public class Member extends Base{
}
