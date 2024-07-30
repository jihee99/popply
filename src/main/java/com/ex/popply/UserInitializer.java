package com.ex.popply;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ex.pop.auth.repository.UserRepository;
import com.ex.pop.user.model.AccountState;
import com.ex.pop.user.model.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = User.builder()
                .email("test@test.com")
                .password(passwordEncoder.encode("1234"))
                .name("홍길동")
                .phoneNumber("010-1234-1234")
                .accountState(AccountState.NORMAL)
                .build();

        userRepository.save(user);
    }
}
