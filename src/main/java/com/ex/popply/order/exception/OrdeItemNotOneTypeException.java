package com.ex.popply.order.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class OrdeItemNotOneTypeException extends CustomException {

    public static final CustomException EXCEPTION = new OrdeItemNotOneTypeException();
    private OrdeItemNotOneTypeException() {
        super(ErrorCode.ORDER_INVALID_ITEM_KIND_POLICY);
    }
}
