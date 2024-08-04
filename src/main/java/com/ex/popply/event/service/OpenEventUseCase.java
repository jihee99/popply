package com.ex.popply.event.service;

import com.ex.popply.event.EventRepository;
import com.ex.popply.event.exception.CannotOpenEventException;
import com.ex.popply.event.model.Event;
import com.ex.popply.event.model.dto.response.EventResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenEventUseCase {
    private final CommonEventService commonEventService;
    private final EventRepository eventRepository;
    public EventResponse execute(Long eventId) {
        final Event event = commonEventService.findById(eventId);
        if (!event.hasEventInfo()) throw CannotOpenEventException.EXCEPTION;
        event.open();
        return EventResponse.of(eventRepository.save(event));
    }
}
