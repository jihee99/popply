package com.ex.popply.order.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class NotOwnerOrderException extends CustomException {

    public static final CustomException EXCEPTION = new NotOwnerOrderException();
    private NotOwnerOrderException() {
        super(ErrorCode.ORDER_NOT_MINE);
    }
}
