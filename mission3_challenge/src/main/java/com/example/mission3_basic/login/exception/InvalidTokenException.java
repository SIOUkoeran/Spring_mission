package com.example.mission3_basic.login.exception;

import com.example.mission3_basic.common.CustomException;
import com.example.mission3_basic.common.ErrorCode;

public class InvalidTokenException extends CustomException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
