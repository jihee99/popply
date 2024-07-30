package com.ex.popply.user.model.dto.response;

import com.ex.pop.user.model.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpResponse {

    @Schema(description = "회원 아이디", example = "1")
    private final Long userId;

    @Schema(description = "회원 이메일", example = "user@test.com")
    private final String email;

    @Schema(description = "회원 이름", example = "홍길동")
    private final String name;

    public static SignUpResponse from(User user){
        return new SignUpResponse(
                user.getUserId(),
                user.getEmail(),
                user.getName()
        );
    }

}
