package com.ex.popply.user.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpRequest {

    @Schema(description = "회원 이메일", example = "test@test.com")
    private final String email;

    @Schema(description = "회원 비밀번호", example = "1234")
    private final String password;

    @Schema(description = "회원 이름", example = "홍길동")
    private final String name;

    @Schema(description = "회원 전화번호", example = "010-1234-1234")
    private final String phoneNumber;

}
