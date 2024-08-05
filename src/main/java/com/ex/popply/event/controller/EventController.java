package com.ex.popply.event.controller;

import java.util.List;

import com.ex.popply.event.model.dto.request.CreateEventInfoRequest;
import com.ex.popply.event.model.dto.request.UpdateEventInfoRequest;
import com.ex.popply.event.model.dto.request.UpdateEventStatusRequest;
import com.ex.popply.event.model.dto.response.EventInfoResponse;
import com.ex.popply.event.service.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ex.popply.event.model.dto.response.EventResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "2. 이벤트 관련 API")
@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

	private final ReadEventUseCase readEventUseCase;
	private final CreateEventUseCase createEventUseCase;
	private final ReadEventInfoUseCase readEventInfoUseCase;
	private final UpdateEventInfoUseCase updateEventInfoUseCase;
	private final OpenEventUseCase openEventUseCase;
	private final UpdateEventStatusUseCase updateEventStatusUseCase;
	private final DeleteEventUseCase deleteEventUseCase;

	@Operation(summary = "사용자가 이벤트 목록을 불러오는 API")
	@GetMapping("/list")
	public ResponseEntity<List<EventResponse>> getAllEvents(){
		return ResponseEntity.ok(readEventUseCase.execute());
	}


	@Operation(summary = "사용자가 이벤트 정보를 불러오는 API")
	@GetMapping("/{eventId}")
	public ResponseEntity<EventInfoResponse> getEvents(@PathVariable Long eventId ) {
		return ResponseEntity.ok(readEventInfoUseCase.execute(eventId));
	}

	@Operation(summary = "매니저가 이벤트 정보를 등록하는 API")
	@PostMapping("/new")
	public ResponseEntity<EventResponse> getEvents( @RequestBody @Valid CreateEventInfoRequest createEventInfoRequest) {
		return ResponseEntity.ok(createEventUseCase.execute(createEventInfoRequest));
	}

	@Operation(summary = "매니저가 이벤트 정보를 수정하는 API")
	@PostMapping("{eventId}/update")
	public ResponseEntity<EventResponse> updateEventInfo(@RequestBody UpdateEventInfoRequest updateEventInfoRequest){
		return ResponseEntity.ok(updateEventInfoUseCase.execute(updateEventInfoRequest));
	}

	@Operation(summary = "이벤트를 오픈 상태로 변경합니다.")
	@GetMapping("/{eventId}/open")
	public EventResponse openEventStatus(@PathVariable Long eventId) {
		return openEventUseCase.execute(eventId);
	}

	@Operation(summary = "이벤트 상태를 변경합니다.")
	@PostMapping("/{eventId}/status")
	public EventResponse closeEventStatus(@PathVariable Long eventId, @RequestBody @Valid UpdateEventStatusRequest updateEventStatusRequest) {
		return updateEventStatusUseCase.execute(eventId, updateEventStatusRequest);
	}

	@Operation(summary = "이벤트를 삭제합니다.")
	@GetMapping("/{eventId}/delete")
	public EventResponse deleteEvent(@PathVariable Long eventId) {
		return deleteEventUseCase.execute(eventId);
	}



}
