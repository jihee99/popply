package com.ex.popply.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex.popply.auth.service.SigninUseCase;
import com.ex.popply.auth.service.SignupUseCase;
import com.ex.popply.user.model.dto.request.SignInRequest;
import com.ex.popply.user.model.dto.request.SignUpRequest;
import com.ex.popply.user.model.dto.response.SignInResponse;
import com.ex.popply.user.model.dto.response.SignUpResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "0. 회원 가입 및 로그인 관련 API")
@RestController
@RequiredArgsConstructor
public class AuthController {

	private final SignupUseCase signupUseCase;
	private final SigninUseCase signinUseCase;

	@Operation(summary = "회원 가입")
	@PostMapping("/new")
	public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest request) {
		// signupUseCase.execute(request);
		return ResponseEntity.ok(signupUseCase.execute(request));
	}

	@Operation(summary = "로그인")
	@PostMapping("/login")
	public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest request) {
		// signinUseCase.execute(request);
		return ResponseEntity.ok(signinUseCase.execute(request));
	}
}
