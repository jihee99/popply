package com.ex.popply.event.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventInfo{
	@Column(length = 25)
	private String name;

	@Column(length = 500)
	private String description;

	private LocalDate startAt;
	private Long period;
	private LocalTime startTime;
	private LocalTime endTime;
	private Long runTime;
	private Long limitPerHour;


	protected Boolean isUpdated() {
		return this.name != null && this.startAt != null && this.runTime != null && this.limitPerHour != null;
	}

	protected LocalDate endAt() {
		if (this.startAt == null || this.period == null || this.endTime == null) {
			return null;
		}
		return this.startAt.plusDays(this.period);
	}

	@Builder
	public EventInfo(String name, LocalDate startAt, Long period,
					 LocalTime startTime, LocalTime endTime, Long runTime, Long limitPerHour) {
		this.name = name;
		this.startAt = startAt;
		this.period = period;
		this.startTime = startTime;
		this.endTime = endTime;
		this.runTime = runTime;
		this.limitPerHour = limitPerHour;
	}

}
