package com.ex.popply.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

//    @ExplainError("사용자 정보를 찾을 수 없을 때 발생하는 오류")
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "ACCOUNT-001", "사용자를 찾을 수 없습니다."),
//    @ExplainError("회원가입시에 이미 회원가입한 유저일시 발생하는 오류")
    USER_ALREADY_SIGNUP(HttpStatus.BAD_REQUEST, "ACCOUNT-002", "이미 회원가입한 유저입니다."),
    PASSWORD_FORMAT_MISMATCH(HttpStatus.BAD_REQUEST, "ACCOUNT-003", "형식에 맞는 비밀번호를 입력하세요."),
//    @ExplainError("정지 처리된 유저일 경우 발생하는 오류")
    SECURITY_CONTEXT_NOT_FOUND(HttpStatus.NOT_FOUND, "ACCOUNT-004", "security context not found"),
    USER_FORBIDDEN(HttpStatus.FORBIDDEN, "ACCOUNT-005", "접근이 제한된 유저입니다."),




    EVENT_NOT_FOUND(HttpStatus.NOT_FOUND, "EVENT-001", "이벤트를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
