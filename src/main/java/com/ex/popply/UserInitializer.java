package com.ex.popply;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ex.popply.auth.repository.UserRepository;
import com.ex.popply.user.model.AccountRole;
import com.ex.popply.user.model.AccountState;
import com.ex.popply.user.model.User;

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

		User user2 = User.builder()
			.email("manager@test.com")
			.password(passwordEncoder.encode("1234"))
			.name("홍길동")
			.phoneNumber("010-1234-1234")
			.accountState(AccountState.NORMAL)
			.accountRole(AccountRole.MANAGER)
			.build();

        userRepository.save(user);
        userRepository.save(user2);
    }
}
