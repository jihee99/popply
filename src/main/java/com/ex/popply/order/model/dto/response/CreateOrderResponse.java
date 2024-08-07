package com.ex.popply.order.model.dto.response;

import com.ex.popply.order.model.Order;
import com.ex.popply.ticket.model.Ticket;
import com.ex.popply.user.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateOrderResponse {

    @Schema(description = "UUID")
    private final String orderId;

    @Schema(description = "주문번호")
    private final String orderNo;

    @Schema(description = "고객이메일")
    private final String customerEmail;

    @Schema(description = "고객이름")
    private final String customerName;

    @Schema(description = "고객 전화번호")
    private final String customerPhoneNumber;


    public static CreateOrderResponse from(Order order, User user) {
        return CreateOrderResponse.builder()
                .customerEmail(user.getEmail())
                .customerName(user.getName())
                .customerPhoneNumber(user.getPhoneNumber())
                .orderId(order.getUuid())
                .orderNo(order.getOrderNo())
                .build();
    }

}
