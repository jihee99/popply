package com.ex.popply.event.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class CannotOpenEventException extends CustomException {

    public static final CustomException EXCEPTION = new CannotOpenEventException();
    private CannotOpenEventException() {
        super(ErrorCode.CANNOT_OPEN_EVENT);
    }
}