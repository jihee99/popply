package com.ex.popply.event.model.dto.response;

import com.ex.popply.common.vo.EventInfoVo;
import com.ex.popply.event.model.Event;
import com.ex.popply.event.model.EventInfo;
import com.ex.popply.event.model.EventStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventInfoResponse {
    private Long eventId;
    private Long userId;
    private EventStatus status;
    private EventInfo eventInfo;

    public static EventInfoResponse of(Event event){
        return EventInfoResponse.builder()
                .eventId(event.getId())
                .userId(event.getUserId())
                .status(event.getStatus())
                .eventInfo(event.getEventInfo())
                .build();
    }
}
