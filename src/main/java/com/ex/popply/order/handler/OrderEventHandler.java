package com.ex.popply.order.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderEventHandler {

//    @EventListener(classes = DoneOrderEvent.class)
//    public void handleDoneOrderEvent(DoneOrderEvent doneOrderEvent) {
//        log.info(doneOrderEvent.getOrderUuid() + "주문 상태 완료, 티켓 생성작업 진행");
//        issuedTicketDomainService.createIssuedTicket(
//                doneOrderEvent.getItemId(),
//                doneOrderEvent.getOrderUuid(),
//                doneOrderEvent.getUserId());
//        log.info(doneOrderEvent.getOrderUuid() + "주문 상태 완료, 티켓 생성작업 완료");
//    }
}
