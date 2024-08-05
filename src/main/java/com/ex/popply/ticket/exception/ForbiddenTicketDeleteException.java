package com.ex.popply.ticket.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class ForbiddenTicketDeleteException extends CustomException {
    public static final CustomException EXCEPTION = new ForbiddenTicketDeleteException();
    private ForbiddenTicketDeleteException() {
        super(ErrorCode.FORBIDDEN_TICKET_DELETE);
    }
}
