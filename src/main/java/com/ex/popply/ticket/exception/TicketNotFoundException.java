package com.ex.popply.ticket.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class TicketNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new TicketNotFoundException();

    private TicketNotFoundException() {
        super(ErrorCode.TICKET_NOT_FOUND);
    }
}
