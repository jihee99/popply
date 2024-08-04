package com.ex.popply.event.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class AlreadyOpenStatusException extends CustomException {

    public static final CustomException EXCEPTION = new AlreadyOpenStatusException();

    private AlreadyOpenStatusException() {
        super(ErrorCode.ALREADY_OPEN_STATUS);
    }
}
