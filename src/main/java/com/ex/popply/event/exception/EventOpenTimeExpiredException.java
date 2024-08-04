package com.ex.popply.event.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class EventOpenTimeExpiredException extends CustomException {
    public static final CustomException EXCEPTION = new EventOpenTimeExpiredException();

    private EventOpenTimeExpiredException() {
        super(ErrorCode.OPEN_TIME_EXPIRED);
    }
}
