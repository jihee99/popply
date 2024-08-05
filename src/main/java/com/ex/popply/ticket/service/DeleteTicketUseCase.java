package com.ex.popply.ticket.service;

import com.ex.popply.ticket.exception.TicketNotFoundException;
import com.ex.popply.ticket.mapper.TicketMapper;
import com.ex.popply.ticket.model.Ticket;
import com.ex.popply.ticket.model.TicketStatus;
import com.ex.popply.ticket.model.dto.response.GetEventTicketResponse;
import com.ex.popply.ticket.model.dto.response.TicketResponse;
import com.ex.popply.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteTicketUseCase {

    private final TicketRepository ticketRepository;
    private final TicketMapper tickeMapper;

    public GetEventTicketResponse execute(Long eventId, Long ticketItemId) {
        Ticket ticket = ticketRepository.findByIdAndTicketStatus(ticketItemId, TicketStatus.VALID)
                .orElseThrow(() -> TicketNotFoundException.EXCEPTION);

        ticket.validateEventId(eventId);
        ticket.deleteTicket();
        ticketRepository.save(ticket);

        return tickeMapper.toGetEventTicketResponse(eventId, true);
    }
}
