package com.ex.popply.ticket.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class TicketQuantityException extends CustomException {

    public static final CustomException EXCEPTION = new TicketQuantityException();

    private TicketQuantityException() {
        super(ErrorCode.TICKET_QUANTITY_LESS_THAN_ZERO);
    }
}
