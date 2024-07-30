package com.ex.popply.user.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInRequest {

    @Schema(description = "회원 이메일", example = "user@test.com")
    private final String email;

    @Schema(description = "회원 비밀번호", example = "1234")
    private final String password;

}
