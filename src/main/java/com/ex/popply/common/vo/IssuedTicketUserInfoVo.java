package com.ex.popply.common.vo;

import com.ex.popply.user.model.User;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class IssuedTicketUserInfoVo {

    private Long userId;

    private String userName;

    private String email;

    private String phoneNumber;

    @Builder
    public IssuedTicketUserInfoVo(
            Long userId, String userName, String email, String phoneNumber) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static IssuedTicketUserInfoVo from(User user) {
        return IssuedTicketUserInfoVo.builder()
                .userId(user.getUserId())
                .userName(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

}
