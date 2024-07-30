package com.ex.popply.event.model;

import java.time.LocalDateTime;

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

	private LocalDateTime startAt;
	private Long runTime;

	protected Boolean isUpdated() {
		return this.name != null && this.startAt != null && this.runTime != null;
	}
	protected LocalDateTime endAt() {
		return this.runTime == null ? null : this.startAt.plusMinutes(this.runTime);
	}

	@Builder
	public EventInfo(String name, String description, LocalDateTime startAt, Long runTime) {
		this.name = name;
		this.description = description;
		this.startAt = startAt;
		this.runTime = runTime;
	}

}
