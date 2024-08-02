package com.ex.popply.event.model;

import com.ex.popply.common.deserializer.CustomEnumDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonDeserialize(using = CustomEnumDeserializer.class)
public enum EventStatus {

	PREPARING("PREPARING"),
	OPEN("OPEN"),
	CLOSED("CLOSED"),
	DELETED("DELETED");

	private final String value;

}
