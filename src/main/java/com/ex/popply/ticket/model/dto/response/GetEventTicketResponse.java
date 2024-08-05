package com.ex.popply.ticket.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
@Builder
public class GetEventTicketResponse {

    @Schema(description = "티켓상품 리스트")
    private List<TicketResponse> ticketItems;

    public static GetEventTicketResponse from(List<TicketResponse> ticketItems) {

        return GetEventTicketResponse.builder().ticketItems(ticketItems).build();
    }
}
