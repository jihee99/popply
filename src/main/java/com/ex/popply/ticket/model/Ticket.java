package com.ex.popply.ticket.model;

import com.ex.popply.ticket.exception.ForbiddenTicketDeleteException;
import com.ex.popply.ticket.exception.InvalidTicketException;
import com.ex.popply.ticket.exception.TicketQuantityException;
import com.ex.popply.ticket.exception.TicketQuantityLackException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Table(name = "tbl_ticket")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_item_id")
    @Comment("티켓 아이디")
    private Long id;

    private Long eventId;

    private String name;

    private String description;

    private Long price;

    @Comment("재고")
    private Long quantity;

    @Comment("공급량")
    private Long supplyCount;

    @Comment("구매 제한 수량")
    private Long purchaseLimit;

    @Comment("판매 가능 여부")
    private Boolean isSellable;

    @Comment("판매 가능 시간")
    private LocalDateTime saleStartAt;

    @Comment("판매 종료 시간")
    private LocalDateTime saleEndAt;

    @Comment("티켓 시작 시간")
    private LocalDateTime startTime;

    @Comment("티켓 종료 시간")
    private LocalDateTime endTime;


    @Enumerated(EnumType.STRING)
    @ColumnDefault(value = "'VALID'")
    @Comment("티켓 상태")
    private TicketStatus ticketStatus = TicketStatus.VALID;


    @Builder
    public Ticket(
            Long eventId,
            String name,
            String description,
            Long price,
            Long quantity,
            Long supplyCount,
            Long purchaseLimit,
            Boolean isSellable,
            LocalDateTime saleStartAt,
            LocalDateTime saleEndAt,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        this.eventId = eventId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.supplyCount = supplyCount;
        this.purchaseLimit = purchaseLimit;
        this.isSellable = isSellable;
        this.saleStartAt = saleStartAt;
        this.saleEndAt = saleEndAt;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Boolean isQuantityReduced() {
        return !this.quantity.equals(this.supplyCount);
    }

    public void reduceQuantity(Long quantity) {
        if (this.quantity < 0) {
            throw TicketQuantityException.EXCEPTION;
        }
        validEnoughQuantity(quantity);
        this.quantity = this.quantity - quantity;
    }

    public void validEnoughQuantity(Long quantity) {
        if (this.quantity < quantity) {
             throw TicketQuantityLackException.EXCEPTION;
        }
    }

    public Boolean isSold() {
        return quantity < supplyCount;
    }

    public Boolean isQuantityLeft() {
        return quantity > 0;
    }

    public void validateEventId(Long eventId) {
        if (!this.getEventId().equals(eventId)) {
            throw InvalidTicketException.EXCEPTION;
        }
    }

    public void deleteTicket() {
        // 재고 감소된 티켓상품은 삭제 불가
        if (this.isQuantityReduced()) {
            throw ForbiddenTicketDeleteException.EXCEPTION;
        }
        this.ticketStatus = TicketStatus.DELETED;
    }


}
