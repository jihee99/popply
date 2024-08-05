package com.ex.popply.ticket.mapper;

import com.ex.popply.common.annotation.Mapper;
import com.ex.popply.event.model.Event;
import com.ex.popply.event.service.CommonEventService;
import com.ex.popply.ticket.model.Ticket;
import com.ex.popply.ticket.model.TicketStatus;
import com.ex.popply.ticket.model.dto.request.CreateTicketRequest;
import com.ex.popply.ticket.model.dto.response.GetEventTicketResponse;
import com.ex.popply.ticket.model.dto.response.TicketResponse;
import com.ex.popply.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@RequiredArgsConstructor
public class TicketMapper {

    private final CommonEventService commonEventService;
    private final TicketRepository ticketRepository;

    public Ticket toTicket(CreateTicketRequest createTicketRequest, Long eventId) {

        return Ticket.builder()
                .eventId(eventId)
                .name(createTicketRequest.getName())
                .description(createTicketRequest.getDescription())
                .price(createTicketRequest.getPrice())
                .quantity(createTicketRequest.getSupplyCount())
                .supplyCount(createTicketRequest.getSupplyCount())
                .isQuantityPublic(createTicketRequest.getIsQuantityPublic())
                .purchaseLimit(createTicketRequest.getPurchaseLimit())
                .isSellable(true)
                .build();
    }

    @Transactional(readOnly = true)
    public GetEventTicketResponse toGetEventTicketResponse(Long eventId, Boolean isAdmin) {
        Event event = commonEventService.findById(eventId);
        List<Ticket> tickets = ticketRepository.findAllByEventIdAndTicketStatus(event.getId(), TicketStatus.VALID);
        return GetEventTicketResponse.from(
                tickets.stream()
                        .map(ticket -> TicketResponse.from(ticket, isAdmin))
                        .toList());
    }

}
