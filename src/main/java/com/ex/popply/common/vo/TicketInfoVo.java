package com.ex.popply.common.vo;

import com.ex.popply.ticket.model.Ticket;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Embeddable
@NoArgsConstructor
public class TicketInfoVo {

    private Long ticketId;
    private String ticketName;
    private LocalDateTime startTime;

    @Builder
    public TicketInfoVo(
            Long ticketId,
            String ticketName,
            LocalDateTime startTime
    ) {
        this.ticketId = ticketId;
        this.ticketName = ticketName;
        this.startTime = startTime;
    }

    public static TicketInfoVo from(Ticket ticket) {
        return TicketInfoVo.builder()
                .ticketId(ticket.getId())
                .ticketName(ticket.getName())
                .startTime(ticket.getStartTime())
                .build();
    }

}
