package com.ex.popply.event.service;

import com.ex.popply.event.model.Event;
import com.ex.popply.event.model.dto.response.EventResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReadEventInfoUseCase {

    private CommonEventService commonEventService;

    public EventResponse execute(Long eventId) {
        final Event event = commonEventService.findById(eventId);

        return null;
    }
}
