package com.ex.popply.ticket.service;

import com.ex.popply.event.EventRepository;
import com.ex.popply.event.exception.EventNotFoundException;
import com.ex.popply.event.model.Event;
import com.ex.popply.ticket.mapper.TicketMapper;
import com.ex.popply.ticket.model.Ticket;
import com.ex.popply.ticket.model.dto.request.CreateTicketRequest;
import com.ex.popply.ticket.model.dto.response.TicketResponse;
import com.ex.popply.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateTicketUseCase {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final TicketMapper ticketMapper;

    public List<TicketResponse> execute(CreateTicketRequest createTicketRequest, Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> EventNotFoundException.EXCEPTION);
        List<Ticket> tickets = generateTickets(event, createTicketRequest, eventId);
        List<Ticket> savedTickets = ticketRepository.saveAll(tickets);

        return savedTickets.stream()
                .map(TicketResponse::from)
                .toList();
//        return TicketResponse.from(ticketRepository.save(ticketMapper.toTicket(createTicketRequest, eventId)));
    }

    private List<Ticket> generateTickets(Event event, CreateTicketRequest request, Long eventId) {

        List<Ticket> tickets = new ArrayList<>();
        LocalDateTime startDateTime = event.getStartAt().atTime(event.getEventInfo().getStartTime());
        LocalDateTime endDateTime = event.getEndAt().atTime(event.getEventInfo().getEndTime());
        Duration runTime = Duration.ofMinutes(event.getEventInfo().getRunTime());

        while (startDateTime.isBefore(endDateTime)) {
            LocalDateTime ticketEndTime = startDateTime.plus(runTime);
            if (ticketEndTime.isAfter(endDateTime)) {
                ticketEndTime = endDateTime;
            }

            Ticket ticket = ticketMapper.toTicket(request, eventId);
            ticket.setStartTime(startDateTime);
            ticket.setEndTime(ticketEndTime);

            tickets.add(ticket);

            startDateTime = ticketEndTime;
        }

        return tickets;
    }

}
