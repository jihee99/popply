package com.ex.popply.order.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class NotPendingOrderException extends CustomException {

    public static final CustomException EXCEPTION = new NotPendingOrderException();

    private NotPendingOrderException() {
        super(ErrorCode.ORDER_NOT_PENDING);
    }
}