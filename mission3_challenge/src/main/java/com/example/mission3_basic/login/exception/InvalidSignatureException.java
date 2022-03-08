package com.example.mission3_basic.login.exception;

import com.example.mission3_basic.common.CustomException;
import com.example.mission3_basic.common.ErrorCode;

public class InvalidSignatureException extends CustomException {
    public InvalidSignatureException() {
        super(ErrorCode.INVALID_SIGNATURE);
    }
}
