package com.ex.popply.user.model.dto.response;

import com.ex.pop.user.model.AccountRole;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInResponse {

    @Schema(description = "회원 아이디", example = "1")
    private final Long userId;

    @Schema(description = "회원 이메일", example = "user@test.com")
    private final String email;

    @Schema(description = "회원 이름", example = "홍길동")
    private final String name;

    @Schema(description = "회원 유형", example = "USER")
    private final AccountRole role;
}
