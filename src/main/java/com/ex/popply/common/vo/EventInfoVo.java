package com.ex.popply.common.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
	private Long limitPerHour;

	public static EventInfoVo from(Event event) {
		EventInfo eventInfo = event.getEventInfo();
		if (eventInfo == null) {
			return EventInfoVo.builder().build();
		}

		LocalDateTime startDateTime = combineDateTime(eventInfo.getStartAt(), eventInfo.getStartTime());
		LocalDateTime endDateTime = combineDateTime(event.getEndAt(), eventInfo.getEndTime());

		return EventInfoVo.builder()
				.name(eventInfo.getName())
				.startAt(startDateTime)
				.endAt(endDateTime)
				.runTime(eventInfo.getRunTime())
				.limitPerHour(eventInfo.getLimitPerHour())
				.build();
	}

	private static LocalDateTime combineDateTime(LocalDate date, LocalTime time) {
		if (date == null || time == null) {
			return null;
		}
		return LocalDateTime.of(date, time);
	}
}
