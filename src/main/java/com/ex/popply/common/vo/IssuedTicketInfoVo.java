package com.ex.popply.common.vo;

import com.ex.popply.common.annotation.DateFormat;
import com.ex.popply.ticket.model.IssuedTicket;
import com.ex.popply.ticket.model.IssuedTicketStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Builder
public class IssuedTicketInfoVo {

    private final Long issuedTicketId;
    private final String issuedTicketNo;
    private final String orderUuid;
    private final String ticketName;
    @DateFormat
    private final LocalDateTime createdAt;
    @DateFormat
    private final LocalDateTime startTime;
    @DateFormat
    private final LocalDateTime enteredAt;
    private final IssuedTicketStatus issuedTicketStatus;

    public static IssuedTicketInfoVo from(IssuedTicket issuedTicket) {
        return IssuedTicketInfoVo.builder()
                .issuedTicketId(issuedTicket.getId())
                .issuedTicketNo(issuedTicket.getIssuedTicketNo())
                .orderUuid(issuedTicket.getOrderUuid())
                .ticketName(issuedTicket.getTicketInfo().getTicketName())
                .createdAt(issuedTicket.getCreatedAt())
                .startTime(issuedTicket.getTicketInfo().getStartTime())
                .issuedTicketStatus(issuedTicket.getIssuedTicketStatus())
                .enteredAt(issuedTicket.getEnteredAt())
                .build();
    }

}
