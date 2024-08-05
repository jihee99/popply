package com.ex.popply.ticket.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class TicketQuantityLackException extends CustomException {

    public static final CustomException EXCEPTION = new TicketQuantityLackException();

    private TicketQuantityLackException() {
        super(ErrorCode.TICKET_QUANTITY_LACK);
    }

}
