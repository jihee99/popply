package com.ex.popply.ticket.model;

import com.ex.popply.common.model.BaseTimeEntity;
import com.ex.popply.common.vo.IssuedTicketInfoVo;
import com.ex.popply.common.vo.TicketInfoVo;
import com.ex.popply.common.vo.IssuedTicketUserInfoVo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="tbl_issued_ticket")
public class IssuedTicket extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issued_ticket_id")
    private Long id;
    private String orderUuid;

    @Column(name = "order_id")
    private Long orderId;

    private String issuedTicketNo;

    private Long eventId;

    @Embedded
    private IssuedTicketUserInfoVo userInfo;

    @Embedded
    private TicketInfoVo ticketInfo;

    private LocalDateTime enteredAt;

    private IssuedTicketStatus issuedTicketStatus = IssuedTicketStatus.ENTRANCE_INCOMPLETE;

    @PrePersist
    public void createUUID() {
        this.orderUuid = UUID.randomUUID().toString();
    }

    public IssuedTicketInfoVo toIssuedTicketInfoVo() {
        return IssuedTicketInfoVo.from(this);
    }
}
