package com.ex.popply.order.repository;

import com.ex.popply.order.model.Order;
import com.ex.popply.order.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByTicketIdAndOrderStatusAndUserId(Long ticketId, OrderStatus orderStatus, Long userId);

}
