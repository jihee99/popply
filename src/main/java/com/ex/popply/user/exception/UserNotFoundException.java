package com.ex.popply.user.exception;

import com.ex.popply.common.exception.CustomException;
import com.ex.popply.common.exception.ErrorCode;

public class UserNotFoundException extends CustomException {

	public static final CustomException EXCEPTION = new UserNotFoundException();

	private UserNotFoundException() {
		super(ErrorCode.USER_NOT_FOUND);
	}
}
