package com.ex.popply.ticket.repository;

import com.ex.popply.ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Boolean existsByEventId(Long eventId);


}
