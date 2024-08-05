package com.ex.popply.ticket.controller;

import com.ex.popply.ticket.model.dto.response.GetEventTicketResponse;
import com.ex.popply.ticket.model.dto.request.CreateTicketRequest;
import com.ex.popply.ticket.model.dto.response.TicketResponse;
import com.ex.popply.ticket.service.CreateTicketUseCase;
import com.ex.popply.ticket.service.DeleteTicketUseCase;
import com.ex.popply.ticket.service.GetEventTicketUseCase;
import io.swagger.v3.oas.annotations.Operation;
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
    private final DeleteTicketUseCase deleteTicketUseCase;
    private final GetEventTicketUseCase getEventTicketUseCase;

    @Operation(summary = "특정 이벤트에 속하는 티켓 상품을 생성합니다.")
    @PostMapping
    public List<TicketResponse> createTicketItem(
            @RequestBody @Valid CreateTicketRequest createTicketRequest,
            @PathVariable Long eventId) {
        return createTicketUseCase.execute(createTicketRequest, eventId);
    }

    @Operation(summary = "티켓을 삭제합니다.")
    @PostMapping("/{ticketItemId}")
    public GetEventTicketResponse deleteTicketItem(
            @PathVariable Long eventId, @PathVariable Long ticketItemId) {
        return deleteTicketUseCase.execute(eventId, ticketItemId);
    }

    @Operation(summary = "해당 이벤트의 티켓을 모두 조회합니다.(admin)", description = "재고 정보가 무조건 공개됩니다.")
    @GetMapping("/admin")
    public GetEventTicketResponse getEventTicketItemsForManager(@PathVariable Long eventId) {
        return getEventTicketUseCase.executeForManager(eventId);
    }




}
