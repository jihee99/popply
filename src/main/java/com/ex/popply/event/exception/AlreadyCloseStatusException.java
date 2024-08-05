package com.ex.popply.event.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class AlreadyCloseStatusException extends CustomException {

    public static final CustomException EXCEPTION = new AlreadyCloseStatusException();
    private AlreadyCloseStatusException() {
        super(ErrorCode.ALREADY_CLOSE_STATUS);
    }
}
