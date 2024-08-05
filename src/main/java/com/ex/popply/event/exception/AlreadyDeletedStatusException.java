package com.ex.popply.event.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class AlreadyDeletedStatusException extends CustomException {

    public static final CustomException EXCEPTION = new AlreadyDeletedStatusException();

    private AlreadyDeletedStatusException() {
        super(ErrorCode.ALREADY_DELETED_STATUS);
    }
}
