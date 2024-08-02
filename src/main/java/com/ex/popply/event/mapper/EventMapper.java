package com.ex.popply.event.mapper;

import com.ex.popply.common.annotation.Mapper;
import com.ex.popply.event.model.Event;
import com.ex.popply.event.model.dto.request.CreateEventRequest;
import com.ex.popply.event.model.dto.response.EventResponse;
import com.ex.popply.event.service.CommonEventService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
@RequiredArgsConstructor
public class EventMapper {


	private final CommonEventService commonEventService;

	public Event toEntity(CreateEventRequest createEventRequest, Long userId) {
		return Event.builder()
			.userId(userId)
			.name(createEventRequest.getName())
			.startAt(createEventRequest.getStartAt())
			.period(createEventRequest.getPeriod())
			.startTime(createEventRequest.getStartTime())
			.endTime(createEventRequest.getEndTime())
			.runTime(createEventRequest.getRunTime())
			.limitPerHour(createEventRequest.getLimitPerHour())
			.build();
	}

	public List<EventResponse> toEventResponse(){
		return commonEventService.findAllByOrderByCreatedAtDesc()
			.stream()
			.map(EventResponse::of)
			.collect(Collectors.toList());
	}



}
