package com.example.mission3_basic.user.exception;

import com.example.mission3_basic.common.CustomException;
import com.example.mission3_basic.common.ErrorCode;

public class UserDuplicateException extends CustomException {

    public UserDuplicateException() {
        super(ErrorCode.USER_DUPLICATE);
    }
}
