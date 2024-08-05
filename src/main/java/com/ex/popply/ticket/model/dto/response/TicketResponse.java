package com.ex.popply.ticket.model.dto.response;

import com.ex.popply.ticket.model.Ticket;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TicketResponse {

    @Schema(description = "티켓상품 id")
    private final Long ticketItemId;

    @Schema(description = "이름")
    private final String ticketName;

    @Schema(description = "설명")
    private final String description;

    @Schema(description = "가격")
    private final Long price;

    @Schema(description = "1인당 구매 제한 매수")
    private final Long purchaseLimit;

    @Schema(description = "공급량")
    private final Long supplyCount;

    @Schema(description = "재고")
    private final Long quantity;

    @Schema(description = "재고공개 여부")
    private final Boolean isQuantityPublic;

    @Schema(description = "재고가 감소한 티켓인지 리턴")
    private final Boolean isSold;

    @Schema(description = "재고가 남아있는지 리턴")
    private final Boolean isQuantityLeft;

    @Schema(description = "티켓 입장 시간")
    private final LocalDateTime startTime;

    @Schema(description = "티켓 종료 시간")
    private final LocalDateTime endTime;

    public static TicketResponse from(Ticket ticket, Boolean isAdmin) {
        return TicketResponse.builder()
                .ticketItemId(ticket.getId())
                .ticketName(ticket.getName())
                .description(ticket.getDescription())
                .price(ticket.getPrice())
                .isQuantityPublic(ticket.getIsQuantityPublic())
                .quantity(
                    isAdmin || ticket.getIsQuantityPublic()
                                ? ticket.getQuantity()
                                : null)
                .purchaseLimit(ticket.getPurchaseLimit())
                .supplyCount(ticket.getSupplyCount())
                .isSold(ticket.isSold())
                .isQuantityLeft(ticket.isQuantityLeft())
                .startTime(ticket.getStartTime())
                .endTime(ticket.getEndTime())
                .build();
    }

}
