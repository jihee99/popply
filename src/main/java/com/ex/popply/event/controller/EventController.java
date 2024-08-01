package com.ex.popply.event.controller;

import java.util.List;

import com.ex.popply.event.service.ReadEventInfoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	private final ReadEventInfoUseCase readEventInfoUseCase;
	@Operation(summary = "사용자가 이벤트 목록을 불러오는 API")
	@GetMapping("/list")
	public ResponseEntity<List<EventResponse>> getAllEvents(){
		return ResponseEntity.ok(readEventUseCase.execute());
	}


	@Operation(summary = "사용자가 이벤트 정보를 불러오는 API")
	@GetMapping("/{eventId}")
	public ResponseEntity<EventResponse> getEvents( @PathVariable Long eventId ) {
		return ResponseEntity.ok(readEventInfoUseCase.execute(eventId));
	}
}
