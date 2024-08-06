package com.ex.popply.ticket.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CreateOrderIssuedTicketRequest {

    @Schema(description = "주문할 티켓 아이디", defaultValue = "1")
    private Long ticketId;

    @Schema(description = "상품 수량", defaultValue = "1")
    private Long quantity;

}
