package com.ex.popply.event.service;

import com.ex.popply.event.EventRepository;
import com.ex.popply.event.exception.EventNotFoundException;
import com.ex.popply.event.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonEventService {

	private final EventRepository eventRepository;

	public List<Event> findAllByOrderByCreatedAtDesc() {
		return eventRepository.findAllByOrderByCreatedAtDesc();
	}

	public Event findById(Long eventId) {
		return eventRepository
				.findById(eventId)
				.orElseThrow(() -> EventNotFoundException.EXCEPTION);
	}

	public List<Event> findAllByUserId(Long userId) {
		return eventRepository.findAllByUserId(userId);
	}


}
