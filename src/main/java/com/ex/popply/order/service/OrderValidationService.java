package com.ex.popply.order.service;

import com.ex.popply.event.EventRepository;
import com.ex.popply.event.exception.EventNotFoundException;
import com.ex.popply.event.model.Event;
import com.ex.popply.event.service.CommonEventService;
import com.ex.popply.order.exception.OrdeItemNotOneTypeException;
import com.ex.popply.order.exception.OrderItemNotFoundException;
import com.ex.popply.order.model.Order;
import com.ex.popply.order.model.OrderStatus;
import com.ex.popply.ticket.exception.TicketNotFoundException;
import com.ex.popply.ticket.model.Ticket;
import com.ex.popply.ticket.model.TicketStatus;
import com.ex.popply.ticket.repository.IssuedTicketRepository;
import com.ex.popply.ticket.repository.TicketRepository;
import com.ex.popply.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderValidationService {

    private final TicketRepository ticketRepository;
    private final IssuedTicketRepository issuedTicketRepository;
    private final EventRepository eventRepository;

    /* 주문 생성 가능 여부 */
    public void validCreate(Order order) {
        Ticket ticket = getTicket(order);
        Event event = getEvent(order);

        // 이벤트가 열려있는지
        validEventIsOpen(event);
        // 예매가능시간이 안 지났는지
        validTicketingTime(event);
        // 재고가 있는지
        validItemStockEnough(order, ticket);
        // 아이템의 종류가 1 종류 인지
        validItemKindIsOneType(order);
        // 아이템 구매 가능 갯수를 넘지 않았는지.
        validItemPurchaseLimit(order, ticket);
    }

    private Ticket getTicket(Order order) {
        Long ticketId = order.getItemId();
        return ticketRepository
                .findByIdAndTicketStatus(ticketId, TicketStatus.VALID)
                .orElseThrow(() -> TicketNotFoundException.EXCEPTION);
    }

    private Event getEvent(Order order) {
        Long eventId = order.getEventId();
        return eventRepository
                .findById(eventId)
                .orElseThrow(() -> EventNotFoundException.EXCEPTION);
    }

    /* 이벤트 오픈 여부 검증 */
    public void validEventIsOpen(Event event) {
        event.validateNotOpenStatus();
    }

    /* 티켓팅 가능 시간인지 검증 */
    public void validTicketingTime(Event event) {
        event.validateTicketingTime();
    }

    /* 재고 여부 검증 */
    public void validItemStockEnough(Order order, Ticket ticket) {
        ticket.validEnoughQuantity(order.getTotalQuantity());
    }

    public void validItemKindIsOneType(Order order) {
        Long itemId = order.getDistinctItemId();
        if (itemId == null) {
            throw OrderItemNotFoundException.EXCEPTION;
        }
    }

    /* 주문 수량이 구매 제한 수량과 일치하는지 검증 */
    public void validItemPurchaseLimit(Order order, Ticket ticket) {
        Long paidTicketCount = issuedTicketRepository.countPaidTickets(order.getUserId(), ticket.getId());
        Long totalIssuedCount = paidTicketCount + order.getTotalQuantity();
        System.out.println("------------validItemPurchaseLimit--------------");
        System.out.println(" paidTicketCount : " + paidTicketCount);
        System.out.println(" totalIssuedCount : " + totalIssuedCount);
        ticket.validPurchaseLimit(totalIssuedCount);
    }

    public void validCanApproveOrder(Order order) {
        validStatusCanApprove(getOrderStatus(order));
        validCanDone(order);
        // 유저가 탈퇴를 안했는지 확인.
//        validUserNotDeleted(order);
    }

    /* 주문 상태가 승인방식의 승인 가능한 상태인지 검증. */
    public void validStatusCanApprove(OrderStatus orderStatus) {
        if (!Objects.equals(orderStatus, OrderStatus.PENDING_APPROVE)) {
//            throw NotPendingOrderException.EXCEPTION;
        }
    }

    private OrderStatus getOrderStatus(Order order) {
        return order.getOrderStatus();
    }

    public void validCanDone(Order order) {
        Ticket ticket = getTicket(order);
        Event event = getEvent(order);

        // 이벤트가 열려있는 상태인지
        validEventIsOpen(event);
        // 티켓 예매 가능 시간이 아직 안지났는지
        validTicketingTime(event);
        // 재고가 충분히 있는지 ( 추후 티켓 발급하면서도 2차로 검증함 )
        validItemStockEnough(order, ticket);
        // 아이템 구매 가능 횟수를 넘지 않는지.
        validItemPurchaseLimit(order, ticket);
    }

}
