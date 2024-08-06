package com.ex.popply.order.model;

import com.ex.popply.order.exception.OrderItemNotFoundException;
import com.ex.popply.ticket.model.Ticket;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_order")
public class Order {
    public static final Long NO_START_NUMBER = 1000000L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long eventId;

    @Column(nullable = false)
    private String uuid;
    private String orderNo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime approvedAt;

    private LocalDateTime withDrawAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.READY; // 주문상태

    @PrePersist
    public void addUUID() {
        this.uuid = UUID.randomUUID().toString();
    }

    @PostPersist
    public void createOrder() {
        this.orderNo = "P" + Long.sum(NO_START_NUMBER, this.id);
    }


    @Builder
    public Order(
            Long userId,
            List<OrderItem> orderItems,
            OrderStatus orderStatus,
            Long eventId
    ) {
        this.userId = userId;
        this.eventId = eventId;
        this.orderItems.addAll(orderItems);
        this.orderStatus = orderStatus;
    }

    public static Order createApproveOrder(
            Long userId, Ticket ticket
    ) {
        Order order = Order.builder()
                .userId(userId)
                .orderItems((List<OrderItem>) getOrderItems(ticket))
                .orderStatus(OrderStatus.PENDING_APPROVE)
                .eventId(ticket.getEventId())
                .build();

//        orderValidator.validCreate(order);
//        orderValidator.validApproveStatePurchaseLimit(order);

        return order;
    }

    public Long getItemId() {
        return getOrderItem().getItemId();
    }

    private OrderItem getOrderItem() {
        return orderItems.stream()
                .findFirst()
                .orElseThrow(() -> OrderItemNotFoundException.EXCEPTION);
    }

    @NotNull
    private static OrderItem getOrderItems(Ticket item) {
        return OrderItem.of(item.getQuantity(), item);
    }

}
