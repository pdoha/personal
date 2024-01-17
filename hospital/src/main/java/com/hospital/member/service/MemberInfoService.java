package com.hospital.member.service;

import com.hospital.member.entities.Member;
import com.hospital.member.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberInfoService implements UserDetailsService {

    //DB에서도 조회할 수 있게 의존성 추가
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws
     UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username) //1번째는 이메일 조회
                .orElseGet(() -> memberRepository.findByUserId(username) //2번째는 아이디로 조회
                        .orElseThrow(() -> new UsernameNotFoundException(username))); //3번째에도 없으면 예외 던진다
        //userDetails 구현체로 반환
        return MemberInfo.builder()
                .email(member.getEmail())
                .userId(member.getUserId())
                .password(member.getPassword())
                .member(member)
                .build();
    }
}
