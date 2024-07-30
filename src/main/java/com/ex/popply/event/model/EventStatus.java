package com.ex.popply.event.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
//@JsonDeserialize(using = CustomEnumDeserializer.class)
public enum EventStatus {

	PREPARING("PREPARING"),
	OPEN("OPEN"),
	CLOSED("CLOSED"),
	DELETED("DELETED");

	private final String value;

}
