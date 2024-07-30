package com.ex.popply.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ex.popply.auth.repository.UserRepository;
import com.ex.popply.user.model.User;
import com.ex.popply.user.model.dto.request.SignInRequest;
import com.ex.popply.user.model.dto.response.SignInResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SigninUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public SignInResponse execute(SignInRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .filter(it -> encoder.matches(request.getPassword(), it.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));

        user.login();

//        String newAccessToken = jwtTokenProvider.generateAccessToken(user.getUserId(), user.getAccountRole().getValue());
//        String newRefreshToken = jwtTokenProvider.generateRefreshToken(user.getUserId());

        return SignInResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .role(user.getAccountRole())
//                .accessToken(newAccessToken)
//                .refreshToken(newRefreshToken)
                .build();
    }
}
