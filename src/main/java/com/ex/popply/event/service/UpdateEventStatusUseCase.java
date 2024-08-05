package com.ex.popply.event.service;

import com.ex.popply.event.EventRepository;
import com.ex.popply.event.exception.UseAnotherApiException;
import com.ex.popply.event.mapper.EventMapper;
import com.ex.popply.event.model.Event;
import com.ex.popply.event.model.EventStatus;
import com.ex.popply.event.model.dto.request.UpdateEventStatusRequest;
import com.ex.popply.event.model.dto.response.EventResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateEventStatusUseCase {

    private final CommonEventService commonEventService;
    private final EventRepository eventRepository;

    public EventResponse execute(Long eventId, UpdateEventStatusRequest updateEventStatusRequest) {
        final Event event = commonEventService.findById(eventId);
        final EventStatus status = updateEventStatusRequest.getStatus();

        if (status == EventStatus.CLOSED) event.close();
        else if (status == EventStatus.PREPARING) event.prepare();
        else throw UseAnotherApiException.EXCEPTION;

        return EventResponse.of(eventRepository.save(event));
    }
}
