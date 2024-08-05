package com.ex.popply.event.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class AlreadyPreparingStatusException extends CustomException {

    public static final CustomException EXCEPTION = new AlreadyPreparingStatusException();

    private AlreadyPreparingStatusException() {
        super(ErrorCode.ALREADY_PREPARING_STATUS);
    }
}
