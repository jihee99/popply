package com.ex.popply.order.repository;

import com.ex.popply.order.model.Order;
import com.ex.popply.order.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM tbl_order o JOIN o.orderItem oi WHERE oi.orderItem.itemId = :ticketId AND o.orderStatus = :orderStatus AND o.userId = :userId")
    List<Order> findByTicketIdAndOrderStatusAndUserId(@Param("ticketId") Long ticketId, @Param("orderStatus") OrderStatus orderStatus, @Param("userId") Long userId);
}
