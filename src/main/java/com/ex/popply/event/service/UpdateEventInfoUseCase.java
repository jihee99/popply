package com.ex.popply.event.service;

 import com.ex.popply.event.EventRepository;
 import com.ex.popply.event.mapper.EventMapper;
import com.ex.popply.event.model.Event;
 import com.ex.popply.event.model.EventInfo;
 import com.ex.popply.event.model.dto.request.CreateEventInfoRequest;
import com.ex.popply.event.model.dto.request.UpdateEventInfoRequest;
import com.ex.popply.event.model.dto.response.EventInfoResponse;
import com.ex.popply.event.model.dto.response.EventResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateEventInfoUseCase {

    private final CommonEventService commonEventService;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    public EventResponse execute(UpdateEventInfoRequest eventInfoRequest){
        final Event event = commonEventService.findById(eventInfoRequest.getEventId());
        event.setEventInfo(eventMapper.toEventInfo(eventInfoRequest));
        EventResponse.of(eventRepository.save(event));
        return EventResponse.of(eventRepository.save(event));
    }
}
