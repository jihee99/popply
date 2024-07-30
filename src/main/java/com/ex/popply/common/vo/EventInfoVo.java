package com.ex.popply.common.vo;

import java.time.LocalDateTime;

import com.ex.popply.event.model.Event;
import com.ex.popply.event.model.EventInfo;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventInfoVo {

	private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime startAt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime endAt;
	private Long runTime;

	public static EventInfoVo from(Event event){
		EventInfo eventInfo = event.getEventInfo();
		if(eventInfo == null) {
			return EventInfoVo.builder().build();
		}
		return EventInfoVo.builder()
			.name(eventInfo.getName())
			.startAt(eventInfo.getStartAt())
			.endAt(event.getEndAt())
			.runTime(eventInfo.getRunTime())
			.build();
	}
}
