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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@SecurityRequirement(name = "access-token")
@Tag(name = "3. 티켓 관리 API")
@RestController
@RequestMapping("/api/events/{eventId}/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final CreateTicketUseCase createTicketUseCase;
    private final DeleteTicketUseCase deleteTicketUseCase;
    private final GetEventTicketUseCase getEventTicketUseCase;

    @Operation(summary = "특정 이벤트에 속하는 티켓 상품을 생성하는 API")
    @PostMapping
    public ResponseEntity<List<TicketResponse>> createTicket(
            @RequestBody @Valid CreateTicketRequest createTicketRequest,
            @PathVariable Long eventId) {
        return ResponseEntity.ok(createTicketUseCase.execute(createTicketRequest, eventId));
    }

    @Operation(summary = "티켓을 삭제하는 API")
    @PostMapping("/{ticketId}")
    public ResponseEntity<GetEventTicketResponse> deleteTicket(
            @PathVariable Long eventId, @PathVariable Long ticketId) {
        return ResponseEntity.ok(deleteTicketUseCase.execute(eventId, ticketId));
    }

    @Operation(summary = "해당 이벤트의 티켓을 모두 조회하는 API")
    @GetMapping("/list")
    public ResponseEntity<GetEventTicketResponse> getEventTicketItems(@PathVariable Long eventId) {
        return ResponseEntity.ok(getEventTicketUseCase.execute(eventId));
    }

    @Operation(summary = "해당 이벤트의 티켓을 모두 조회하는 API(admin)", description = "재고 정보가 공개됩니다.")
    @GetMapping("/admin")
    public ResponseEntity<GetEventTicketResponse> getEventTicketItemsForManager(@PathVariable Long eventId) {
        return ResponseEntity.ok(getEventTicketUseCase.executeForManager(eventId));
    }


}
