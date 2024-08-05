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


    EVENT_NOT_FOUND(HttpStatus.NOT_FOUND, "EVENT-001", "이벤트를 찾을 수 없습니다."),
    CANNOT_OPEN_EVENT(HttpStatus.BAD_REQUEST, "EVENT-002", "오픈 조건을 충족하지 않았습니다."),
    ALREADY_OPEN_STATUS(HttpStatus.BAD_REQUEST, "EVENT-003", "이미 오픈 중인 이벤트입니다."),
    OPEN_TIME_EXPIRED(HttpStatus.BAD_REQUEST, "EVENT-004", "오픈 예정 시간이 현재 시간보다 빠릅니다."),
    ALREADY_CLOSE_STATUS(HttpStatus.BAD_REQUEST, "EVENT-005", "이미 종료된 이벤트입니다."),
    ALREADY_PREPARING_STATUS(HttpStatus.BAD_REQUEST, "EVENT-006", "이미 준비중인 이벤트입니다."),
    ALREADY_DELETED_STATUS(HttpStatus.BAD_REQUEST, "EVENT-007", "이미 삭제된 이벤트입니다."),
    USE_ANOTHER_API(HttpStatus.BAD_REQUEST, "EVENT-008", "잘못된 접근입니다."),
    CANNOT_DELETE_BY_ISSUED_TICKET(HttpStatus.BAD_REQUEST, "EVENT-009", "발급 티켓이 있는 이벤트는 삭제할 수 없습니다."),
    CANNOT_DELETE_BY_OPEN_EVENT(HttpStatus.BAD_REQUEST, "EVENT-010", "오픈 상태인 이벤트는 삭제할 수 없습니다."),


//    @ExplainError("주문 및 승인 요청 시 티켓 상품 재고보다 많은 양을 주문 시 발생하는 오류입니다.")
    TICKET_QUANTITY_LESS_THAN_ZERO(HttpStatus.BAD_REQUEST, "TICKET-001", "티켓 재고가 0보다 작을 수 없습니다."),
//    @ExplainError("주문 요청한 티켓 상품 재고가 부족할 때 발생하는 오류입니다.")
    TICKET_QUANTITY_LACK(HttpStatus.BAD_REQUEST, "TICKET-002", "티켓 상품 재고가 부족합니다."),

//    @ExplainError("요청에서 보내준 티켓 상품 id 값이 올바르지 않을 때 발생하는 오류입니다.")
    TICKET_NOT_FOUND(HttpStatus.NOT_FOUND, "TICKET-003", "티켓 아이템을 찾을 수 없습니다."),
    INVALID_TICKET(HttpStatus.BAD_REQUEST, "TICKET-004", "해당 이벤트 소속의 티켓이 아닙니다."),
//    @ExplainError("이미 재고가 감소되어 티켓상품 삭제가 불가능할 경우 발생하는 오류입니다.")
    FORBIDDEN_TICKET_DELETE(HttpStatus.BAD_REQUEST, "TICKET-005", "티켓상품 삭제가 불가능한 상태입니다."),

    ORDER_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "ORDER-001", "주문상품을 찾을 수 없습니다.")

    ;
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
