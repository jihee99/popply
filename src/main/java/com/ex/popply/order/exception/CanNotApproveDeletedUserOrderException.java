package com.ex.popply.order.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class CanNotApproveDeletedUserOrderException extends CustomException {

    public static final CustomException EXCEPTION =
            new CanNotApproveDeletedUserOrderException();

    private CanNotApproveDeletedUserOrderException() {
        super(ErrorCode.CAN_NOT_DELETED_USER_APPROVE);
    }
}
