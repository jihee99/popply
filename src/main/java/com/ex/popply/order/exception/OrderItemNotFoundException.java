package com.ex.popply.order.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class OrderItemNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new OrderItemNotFoundException();
    private OrderItemNotFoundException() {
        super(ErrorCode.ORDER_ITEM_NOT_FOUND);
    }
}
