package com.ex.popply.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ex.pop.auth.repository.UserRepository;
import com.ex.pop.user.model.User;
import com.ex.pop.user.model.dto.request.SignUpRequest;
import com.ex.pop.user.model.dto.response.SignUpResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignupUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,20}$";

    public SignUpResponse execute(SignUpRequest request) {
        validateMemberNotExist(request.getEmail());
        validatePasswordForm(request.getPassword());
        User user = userRepository.save(new User(request, encoder));
        return SignUpResponse.from(user);
    }

    private void validateMemberNotExist(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            log.info("[회원가입 실패] 중복 이메일 회원가입 시도 -> email : " + email);
//            throw AlreadySignUpUserException.EXCEPTION;
        }
    }

    private void validatePasswordForm(String password) {
        if (!password.matches(PASSWORD_REGEX)) {
//            throw PasswordFormatMismatchException.EXCEPTION;
        }
    }
}
