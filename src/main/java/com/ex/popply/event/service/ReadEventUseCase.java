package com.ex.popply.event.service;

import java.util.List;

import com.ex.popply.event.mapper.EventMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ex.popply.event.model.dto.response.EventResponse;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReadEventUseCase {

	private final EventMapper eventMapper;

	public List<EventResponse> execute(){
		return eventMapper.toEventResponse();
	}
}
