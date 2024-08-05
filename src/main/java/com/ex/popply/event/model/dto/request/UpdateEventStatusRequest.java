package com.ex.popply.event.model.dto.request;

import com.ex.popply.common.annotation.Enum;

import com.ex.popply.event.model.EventStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateEventStatusRequest {
    @Schema(defaultValue = "OPEN", description = "오픈 상태")
    @Enum(message = "올바른 상태값을 입력해주세요.")
    private EventStatus status;
}
