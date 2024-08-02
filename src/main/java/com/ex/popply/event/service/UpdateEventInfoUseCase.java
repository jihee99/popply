package com.ex.popply.event.service;

import com.ex.popply.event.model.dto.request.CreateEventInfoRequest;
import com.ex.popply.event.model.dto.request.UpdateEventInfoRequest;
import com.ex.popply.event.model.dto.response.EventInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateEventInfoUseCase {

    public EventInfoResponse execute(UpdateEventInfoRequest eventInfoRequest){

        return null;
    }
}
