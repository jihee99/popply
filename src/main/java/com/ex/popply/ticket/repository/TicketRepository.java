package com.ex.popply.ticket.repository;

import com.ex.popply.ticket.model.Ticket;
import com.ex.popply.ticket.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Boolean existsByEventId(Long eventId);
    Optional<Ticket> findByIdAndTicketStatus(Long ticketId, TicketStatus status);
    List<Ticket> findAllByEventIdAndTicketStatus(Long eventId, TicketStatus status);

}
