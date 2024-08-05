package com.ex.popply.event.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class CannotDeleteByOpenEventException extends CustomException {

    public static final CustomException EXCEPTION = new CannotDeleteByOpenEventException();

    private CannotDeleteByOpenEventException() {
        super(ErrorCode.CANNOT_DELETE_BY_OPEN_EVENT);
    }
}
