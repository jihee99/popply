package com.ex.popply.event.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class EventNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new EventNotFoundException();

    private EventNotFoundException() { super(ErrorCode.EVENT_NOT_FOUND); }
}
