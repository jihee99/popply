package com.ex.popply.order.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    // 최초 상태
    READY("READY", "주문 생성상태"),
    PENDING_APPROVE("PENDING_APPROVE", "승인 대기중"),
    APPROVED("APPROVED", "승인 완료"),
    // 사용자가 환불
    REFUND("REFUND", "환불 완료"),
    // 취소된 결제
    CANCELED("CANCELED", "취소된 결제");


    private String value;

    @JsonValue
    private String kr;

    public Boolean isInEventOrderExcelStatus() {
        return this == OrderStatus.CANCELED
                || this == OrderStatus.APPROVED
                || this == OrderStatus.REFUND;
    }

    public Boolean isCanDone() {
        return this == OrderStatus.PENDING_APPROVE;
    }

    public Boolean isCanWithDraw() {
        return this == OrderStatus.APPROVED;
    }
}