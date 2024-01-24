package com.hospital.configs;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    //로그인한 사용자정보를 직접 자동으로 넣어준다

    @Override
    public Optional<String> getCurrentAuditor(){
        return Optional.empty();
    }
}
