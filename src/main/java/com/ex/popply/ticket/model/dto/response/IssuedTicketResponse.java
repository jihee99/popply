package com.ex.popply.ticket.model.dto.response;

import com.ex.popply.common.annotation.DateFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class IssuedTicketResponse {

    @Schema(description = "티켓명", defaultValue = "테스트용 티켓")
    private final String ticketName;
    @Schema(description = "예매 번호", defaultValue = "1")
    private final String orderNo;
    @Schema(description = "티켓 번호", defaultValue = "1")
    private final String ticketNos;
    @Schema(description = "구매 일시")
    @DateFormat
    private final LocalDateTime paymentAt;
    @Schema(description = "유저이름")
    private final String userName;
    @Schema(description = "구매수량")
    private final Long purchaseQuantity;


}
