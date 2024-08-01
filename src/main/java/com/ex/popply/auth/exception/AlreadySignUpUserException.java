package com.ex.popply.auth.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class AlreadySignUpUserException extends CustomException {
    public static final CustomException EXCEPTION = new AlreadySignUpUserException();

    private AlreadySignUpUserException(){
        super(ErrorCode.USER_ALREADY_SIGNUP);
    }
}
