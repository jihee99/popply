package com.ex.popply.order.model;

import com.ex.popply.order.exception.OrderItemNotFoundException;
import com.ex.popply.order.service.OrderValidationService;
import com.ex.popply.ticket.model.Ticket;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

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
            OrderItem orderItem,
            OrderStatus orderStatus,
            Long eventId
    ) {
        this.userId = userId;
        this.eventId = eventId;
        this.orderItem = orderItem;
        this.orderStatus = orderStatus;
    }

    public static Order createApproveOrder(
            Long userId, Ticket ticket, OrderItem orderItem, OrderValidationService orderValidator
    ) {
        Order order = Order.builder()
                .userId(userId)
                .orderItem(orderItem)
                .orderStatus(OrderStatus.PENDING_APPROVE)
                .eventId(ticket.getEventId())
                .build();

        orderValidator.validCreate(order);
//        orderValidator.validApproveStatePurchaseLimit(order);

        return order;
    }

    public void approve(Long currentUserId, Order order, OrderValidationService orderValidator) {
        // 선착순 방식의 0원 결제시
        orderValidator.validCanApproveOrder(order);
        /*주문상태가 0일때*/
//        orderValidator.validCanFreeConfirm(this);
        this.approvedAt = LocalDateTime.now();
        this.orderStatus = OrderStatus.APPROVED;
    }

    public Long getItemId() {
        return getOrderItem().getItemId();
    }

    private OrderItem getOrderItem() {
        if (orderItem == null) {
            throw OrderItemNotFoundException.EXCEPTION;
        }
        return orderItem;
    }

    @NotNull
    private static OrderItem getOrderItems(Ticket ticket) {
        return OrderItem.of(ticket.getQuantity(), ticket);
    }

    public Long getTotalQuantity() {
        System.out.println("--------------getTotalQuantity--------------");
        System.out.println("Order Item: " + orderItem.getQuantity());
        return orderItem != null ? orderItem.getQuantity() : 0L;
    }

    public Long getDistinctItemId() {
        return orderItem != null ? orderItem.getItemId() : null;
    }

}
