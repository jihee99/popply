package com.ex.popply.event.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex.popply.event.model.dto.response.EventResponse;
import com.ex.popply.event.service.ReadEventUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Event", description = "이벤트 관련 API 입니다.")
@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

	private final ReadEventUseCase readEventUseCase;

	@Operation(summary = "사용자가 이벤트 목록을 불러오는 API")
	public ResponseEntity<List<EventResponse>> getAllEvents(){
		return ResponseEntity.ok(readEventUseCase.execute());
	}

}
