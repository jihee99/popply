package com.ex.popply.ticket.repository;

import com.ex.popply.ticket.model.IssuedTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IssuedTicketRepository extends JpaRepository<IssuedTicket, Long> {

    Boolean existsByEventId(Long eventId);

    @Query("SELECT COUNT(i) FROM tbl_issued_ticket i WHERE i.userInfo.userId = :userId AND i.ticketInfo.ticketId = :ticketId AND i.issuedTicketStatus IN (com.ex.popply.ticket.model.IssuedTicketStatus.ENTRANCE_COMPLETED, com.ex.popply.ticket.model.IssuedTicketStatus.ENTRANCE_INCOMPLETE)")
    Long countPaidTickets(@Param("userId") Long userId, @Param("ticketId") Long ticketId);


}
