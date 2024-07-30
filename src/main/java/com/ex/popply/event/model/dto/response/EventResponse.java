package com.ex.popply.event.model.dto.response;

import com.ex.popply.common.vo.EventInfoVo;
import com.ex.popply.event.model.Event;
import com.ex.popply.event.model.EventStatus;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventResponse {

	private Long eventId;
	private Long userId;
	private EventStatus status;
	@JsonUnwrapped
	private EventInfoVo eventInfo;

	public static EventResponse of(Event event){
		return EventResponse.builder()
			.eventId(event.getId())
			.userId(event.getUserId())
			.status(event.getStatus())
			.eventInfo(EventInfoVo.from(event))
			.build();
	}
}
