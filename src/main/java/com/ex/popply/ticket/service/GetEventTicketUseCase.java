package com.ex.popply.ticket.service;

import com.ex.popply.ticket.mapper.TicketMapper;
import com.ex.popply.ticket.model.dto.response.GetEventTicketResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetEventTicketUseCase {
    private final TicketMapper ticketMapper;

    public GetEventTicketResponse execute(Long eventId) {
        return ticketMapper.toGetEventTicketResponse(eventId, false);
    }

    public GetEventTicketResponse executeForManager(Long eventId) {
        return ticketMapper.toGetEventTicketResponse(eventId, true);
    }
}
