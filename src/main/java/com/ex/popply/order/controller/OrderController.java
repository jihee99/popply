package com.ex.popply.order.controller;

import com.ex.popply.order.service.CreateOrderUseCase;
import com.ex.popply.ticket.model.dto.request.CreateOrderIssuedTicketRequest;
import com.ex.popply.ticket.model.dto.response.IssuedTicketResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "4. 주문 관리 API")
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    @Operation(summary = "해당 이벤트의 티켓을 발급받는 API")
    @PostMapping("/{eventId}/issue")
    public ResponseEntity<IssuedTicketResponse> issuedEventTicket(@PathVariable Long eventId, @RequestBody @Valid CreateOrderIssuedTicketRequest createOrderIssuedTicketRequest){
        createOrderUseCase.execute(createOrderIssuedTicketRequest, eventId);
        return null;
    }

}
