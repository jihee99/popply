package com.ex.popply.event.controller;

import java.util.List;

import com.ex.popply.event.model.dto.request.CreateEventInfoRequest;
import com.ex.popply.event.model.dto.request.UpdateEventInfoRequest;
import com.ex.popply.event.model.dto.response.EventInfoResponse;
import com.ex.popply.event.service.CreateEventUseCase;
import com.ex.popply.event.service.ReadEventInfoUseCase;
import com.ex.popply.event.service.UpdateEventInfoUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ex.popply.event.model.dto.response.EventResponse;
import com.ex.popply.event.service.ReadEventUseCase;

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
	@PostMapping("{eventId}/info")
	public ResponseEntity<EventInfoResponse> updateEventInfo(@RequestBody UpdateEventInfoRequest updateEventInfoRequest){

		return ResponseEntity.ok(updateEventInfoUseCase.execute(updateEventInfoRequest));
	}
}
