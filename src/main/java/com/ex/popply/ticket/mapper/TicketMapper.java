package com.ex.popply.ticket.mapper;

import com.ex.popply.common.annotation.Mapper;
import com.ex.popply.event.service.CommonEventService;
import com.ex.popply.ticket.model.Ticket;
import com.ex.popply.ticket.model.dto.request.CreateTicketRequest;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class TicketMapper {

    private final CommonEventService commonEventService;

    public Ticket toTicket(CreateTicketRequest createTicketRequest, Long eventId) {

        return Ticket.builder()
                .eventId(eventId)
                .name(createTicketRequest.getName())
                .description(createTicketRequest.getDescription())
                .price(createTicketRequest.getPrice())
                .quantity(createTicketRequest.getSupplyCount())
                .supplyCount(createTicketRequest.getSupplyCount())
                .purchaseLimit(createTicketRequest.getPurchaseLimit())
                .isSellable(true)
                .build();
    }
}
