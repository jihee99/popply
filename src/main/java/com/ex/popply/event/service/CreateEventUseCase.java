package com.ex.popply.event.service;

import org.springframework.stereotype.Service;

import com.ex.popply.event.model.dto.request.CreateEventRequest;
import com.ex.popply.event.model.dto.response.EventResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateEventUseCase {
	public EventResponse execute(CreateEventRequest event){
		return null;
	}
}
