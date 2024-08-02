package com.ex.popply.event.service;

import com.ex.popply.common.util.UserUtil;
import com.ex.popply.event.EventRepository;
import com.ex.popply.event.mapper.EventMapper;
import com.ex.popply.event.model.Event;
import org.springframework.stereotype.Service;

import com.ex.popply.event.model.dto.request.CreateEventRequest;
import com.ex.popply.event.model.dto.response.EventResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateEventUseCase {

	private final UserUtil userUtil;
	private final EventMapper eventMapper;

	private final EventRepository eventRepository;

	public EventResponse execute(CreateEventRequest createEventRequest){
		final Long userId = userUtil.getCurrentUserId();

		// userId 의 권한이 MANAGER인지 검증 후 set
		Event event = eventMapper.toEntity(createEventRequest, userId);
		return EventResponse.of(eventRepository.save(event));
	}
}
