package com.ex.popply.common.vo;

import com.ex.popply.ticket.model.Ticket;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Embeddable
@RequiredArgsConstructor
public class OrderItemVo {

    private String name;

    private Long price;

    private Long itemId;


    @Builder
    public OrderItemVo(String name, Long price, Long itemId) {
        this.name = name;
        this.price = price;
        this.itemId = itemId;
    }

    public static OrderItemVo from(Ticket ticket) {
        return OrderItemVo.builder()
                .itemId(ticket.getId())
                .price(ticket.getPrice())
                .name(ticket.getName())
                .build();
    }
}
