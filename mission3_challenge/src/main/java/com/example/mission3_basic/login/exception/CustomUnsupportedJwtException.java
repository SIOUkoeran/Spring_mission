package com.example.mission3_basic.login.exception;

import com.example.mission3_basic.common.CustomException;
import com.example.mission3_basic.common.ErrorCode;

public class CustomUnsupportedJwtException extends CustomException {
    public CustomUnsupportedJwtException(){
        super(ErrorCode.UNSUPPORTED_TOKEN);
    }
}
