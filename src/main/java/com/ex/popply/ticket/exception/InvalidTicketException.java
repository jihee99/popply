package com.ex.popply.ticket.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class InvalidTicketException extends CustomException {

    public static final CustomException EXCEPTION = new InvalidTicketException();

    private InvalidTicketException() {
        super(ErrorCode.INVALID_TICKET);
    }
}
