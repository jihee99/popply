package com.ex.popply.user.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class ForbiddenUserException extends CustomException {
    public static final CustomException EXCEPTION = new PasswordFormatMismatchException();
    public ForbiddenUserException() {
        super(ErrorCode.USER_FORBIDDEN);
    }
}
