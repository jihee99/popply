package com.ex.popply.ticket.service;

import com.ex.popply.common.util.UserUtil;
import com.ex.popply.event.EventRepository;
import com.ex.popply.event.exception.EventNotFoundException;
import com.ex.popply.event.model.Event;
import com.ex.popply.event.service.CommonEventService;
import com.ex.popply.ticket.exception.TicketNotFoundException;
import com.ex.popply.ticket.model.IssuedTicket;
import com.ex.popply.ticket.model.Ticket;
import com.ex.popply.ticket.model.TicketStatus;
import com.ex.popply.ticket.model.dto.request.CreateIssuedTicketRequest;
import com.ex.popply.ticket.model.dto.response.IssuedTicketResponse;
import com.ex.popply.ticket.repository.TicketRepository;
import com.ex.popply.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateIssuedTicketUseCase {

    private final UserUtil userUtils;
    private final CommonEventService commonEventService;
    private final TicketRepository ticketRepository;

    public IssuedTicketResponse execute(CreateIssuedTicketRequest issuedTicketRequest, Long eventId){
        User user = userUtils.getCurrentUser();
        Event event = commonEventService.findById(eventId);

        IssuedTicket issuedTicket = createIssuedTicket(event, issuedTicketRequest, user);
        return null;
    }

    private IssuedTicket createIssuedTicket(Event event, CreateIssuedTicketRequest issuedTicketRequest, User user){
        Ticket ticket = ticketRepository.findByIdAndTicketStatus(issuedTicketRequest.getTicketId(), TicketStatus.VALID)
                .orElseThrow(() -> TicketNotFoundException.EXCEPTION);

        return null;

    }
}
