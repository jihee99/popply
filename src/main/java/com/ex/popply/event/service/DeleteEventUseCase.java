package com.ex.popply.event.service;

import com.ex.popply.event.EventRepository;
import com.ex.popply.event.model.Event;
import com.ex.popply.event.model.dto.response.EventResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteEventUseCase {
    private final CommonEventService commonEventService;

//    private final CommonIssuedTicketService commonIssuedTicketService;
    private final EventRepository eventRepository;

    public EventResponse execute(Long eventId) {
        final Event event = commonEventService.findById(eventId);

        // 발급된 티켓이 있는지 검증
//        if (commonIssuedTicketService.existsByEventId(event.getId()))
//            throw CannotDeleteByIssuedTicketException.EXCEPTION;
        event.delete();

        return EventResponse.of(eventRepository.save(event));
    }

}