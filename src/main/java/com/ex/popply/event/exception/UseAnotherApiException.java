package com.ex.popply.event.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class UseAnotherApiException extends CustomException {
    public static final CustomException EXCEPTION = new UseAnotherApiException();
    private UseAnotherApiException() {super(ErrorCode.USE_ANOTHER_API);}
}
