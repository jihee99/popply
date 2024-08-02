package com.ex.popply.common.exception;

import com.ex.popply.user.exception.AlreadySignUpUserException;

public class SecurityContextNotFoundException extends CustomException{
    public static final CustomException EXCEPTION = new SecurityContextNotFoundException();
    private SecurityContextNotFoundException(){
        super(ErrorCode.USER_ALREADY_SIGNUP);
    }
}
