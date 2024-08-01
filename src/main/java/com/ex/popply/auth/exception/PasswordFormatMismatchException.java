package com.ex.popply.auth.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class PasswordFormatMismatchException extends CustomException {
    public static final CustomException EXCEPTION = new PasswordFormatMismatchException();
    public PasswordFormatMismatchException() {
        super(ErrorCode.PASSWORD_FORMAT_MISMATCH);
    }
}
