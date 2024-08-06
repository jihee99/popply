package com.ex.popply.order.model;

import com.ex.popply.common.model.BaseTimeEntity;
import com.ex.popply.common.vo.OrderItemVo;
import com.ex.popply.ticket.model.Ticket;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_order_item")
public class OrderItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @Embedded
    private OrderItemVo orderItem;

    @Column(nullable = false)
    private Long quantity;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Builder
    public OrderItem(OrderItemVo orderItemVo, Long quantity) {
        this.orderItem = orderItemVo;
        this.quantity = quantity;
    }

    @Builder
    public static OrderItem of(Long quantity, Ticket ticket) {
        return OrderItem.builder()
                .quantity(quantity)
                .orderItemVo(OrderItemVo.from(ticket))
                .build();
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getItemId() {
        return orderItem.getItemId();
    }

}
