package com.ex.popply.event.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class EventNotOpenException extends CustomException {

    public static final CustomException EXCEPTION = new EventNotOpenException();
    private EventNotOpenException() { super(ErrorCode.EVENT_NOT_OPEN); }
}
