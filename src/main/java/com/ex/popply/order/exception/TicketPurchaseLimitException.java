package com.ex.popply.order.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class TicketPurchaseLimitException extends CustomException {

    public static final CustomException EXCEPTION = new TicketPurchaseLimitException();

    private TicketPurchaseLimitException(){
        super(ErrorCode.TICKET_ITEM_PURCHASE_LIMIT);
    }
}
