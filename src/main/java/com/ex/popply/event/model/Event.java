package com.ex.popply.event.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ex.popply.common.model.BaseTimeEntity;
import lombok.Builder;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "status != 'DELETED'")
@Entity(name = "tbl_event")
public class Event extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Long id;
	private Long userId;
	@Embedded
	private EventInfo eventInfo;
	@Enumerated(EnumType.STRING)
	private EventStatus status = EventStatus.PREPARING;


	public LocalDate getStartAt() {
		if (this.eventInfo == null) return null;
		return this.getEventInfo().getStartAt();
	}

	public LocalDate getEndAt() {
		if (this.eventInfo == null) return null;
		return this.getEventInfo().endAt();
	}

	@Builder
	public Event(Long userId, String name, String description, LocalDate startAt, Long period,
				 LocalTime startTime, LocalTime endTime, Long runTime, Long limitPerHour) {
		this.userId = userId;
		this.eventInfo = EventInfo.builder()
				.name(name)
				.description(description)
				.startAt(startAt)
				.period(period)
				.startTime(startTime)
				.endTime(endTime)
				.runTime(runTime)
				.limitPerHour(limitPerHour)
				.build();
	}

	public void setEventInfo(EventInfo eventInfo){
		this.eventInfo = eventInfo;
	}

	public Boolean hasEventInfo() {
		return this.eventInfo != null && this.eventInfo.isUpdated() ;
	}

	public Boolean isPreparing() {
		return this.status == EventStatus.PREPARING;
	}

	public Boolean isClosed() {
		return this.status == EventStatus.CLOSED;
	}



}
