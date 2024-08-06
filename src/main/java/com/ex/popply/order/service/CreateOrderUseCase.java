package com.ex.popply.order.service;

import com.ex.popply.auth.repository.UserRepository;
import com.ex.popply.event.model.Event;
import com.ex.popply.event.service.CommonEventService;
import com.ex.popply.order.model.Order;
import com.ex.popply.order.model.OrderItem;
import com.ex.popply.order.repository.OrderRepository;
import com.ex.popply.ticket.exception.TicketNotFoundException;
import com.ex.popply.ticket.model.Ticket;
import com.ex.popply.ticket.model.TicketStatus;
import com.ex.popply.ticket.model.dto.request.CreateOrderIssuedTicketRequest;
import com.ex.popply.ticket.model.dto.response.IssuedTicketResponse;
import com.ex.popply.ticket.repository.TicketRepository;
import com.ex.popply.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateOrderUseCase {

//    private final UserUtil userUtils;
    private final UserRepository userRepository;
    private final CommonEventService commonEventService;
    private final TicketRepository ticketRepository;
    private final OrderRepository orderRepository;
    private final OrderValidationService orderValidationService;

    public IssuedTicketResponse execute(CreateOrderIssuedTicketRequest issuedTicketRequest, Long eventId){
//        User user = userUtils.getCurrentUser();
        // 임시
        User user = userRepository.findById(1L)
                .orElseThrow(() -> {
            throw new AuthenticationException("There is no such user : " + 1L) {};
        });
        Event event = commonEventService.findById(eventId);
        Order order = createOrderIssuedTicket(event, issuedTicketRequest, user);

        orderRepository.save(order);
        log.info("{}", order.toString());
        return null;
    }

    private Order createOrderIssuedTicket(Event event, CreateOrderIssuedTicketRequest orderIssuedTicketRequest, User user){
        Ticket ticket = ticketRepository
                .findByIdAndTicketStatus(orderIssuedTicketRequest.getTicketId(), TicketStatus.VALID)
                .orElseThrow(() -> TicketNotFoundException.EXCEPTION);
        OrderItem orderItem = OrderItem.of(orderIssuedTicketRequest.getQuantity(), ticket);

        return Order.createApproveOrder(user.getUserId(), ticket, orderItem, orderValidationService);
    }
}
