package com.ex.popply.event.mapper;

import com.ex.popply.common.annotation.Mapper;
import com.ex.popply.event.model.Event;
import com.ex.popply.event.model.EventInfo;
import com.ex.popply.event.model.dto.request.CreateEventInfoRequest;
import com.ex.popply.event.model.dto.request.UpdateEventInfoRequest;
import com.ex.popply.event.model.dto.response.EventResponse;
import com.ex.popply.event.service.CommonEventService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
@RequiredArgsConstructor
public class EventMapper {


	private final CommonEventService commonEventService;

	public Event toEntity(CreateEventInfoRequest createEventInfoRequest, Long userId) {
		return Event.builder()
			.userId(userId)
			.name(createEventInfoRequest.getName())
			.description(createEventInfoRequest.getDescription())
			.startAt(createEventInfoRequest.getStartAt())
			.period(createEventInfoRequest.getPeriod())
			.startTime(createEventInfoRequest.getStartTime())
			.endTime(createEventInfoRequest.getEndTime())
			.runTime(createEventInfoRequest.getRunTime())
			.limitPerHour(createEventInfoRequest.getLimitPerHour())
			.build();
	}

	public List<EventResponse> toEventResponse(){
		return commonEventService.findAllByOrderByCreatedAtDesc()
			.stream()
			.map(EventResponse::of)
			.collect(Collectors.toList());
	}

	public EventInfo toEventInfo(UpdateEventInfoRequest eventInfoRequest) {
		return EventInfo.builder()
				.name(eventInfoRequest.getName())
				.description(eventInfoRequest.getDescription())
				.limitPerHour(eventInfoRequest.getLimitPerHour())
				.startAt(eventInfoRequest.getStartAt())
				.period(eventInfoRequest.getPeriod())
				.startTime(eventInfoRequest.getStartTime())
				.endTime(eventInfoRequest.getEndTime())
				.runTime(eventInfoRequest.getRunTime())
				.build();
	}
}
