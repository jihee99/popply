package com.ex.popply.ticket.controller;

import com.ex.popply.ticket.model.dto.request.CreateTicketRequest;
import com.ex.popply.ticket.model.dto.response.TicketResponse;
import com.ex.popply.ticket.service.CreateTicketUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@SecurityRequirement(name = "access-token")
@Tag(name = "4. 티켓 상품 관리 API")
@RestController
@RequestMapping("/api/events/{eventId}/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final CreateTicketUseCase createTicketUseCase;

    @Operation(summary = "특정 이벤트에 속하는 티켓 상품을 생성합니다.")
    @PostMapping
    public List<TicketResponse> createTicketItem(
            @RequestBody @Valid CreateTicketRequest createTicketRequest,
            @PathVariable Long eventId) {
        return createTicketUseCase.execute(createTicketRequest, eventId);
    }



}
