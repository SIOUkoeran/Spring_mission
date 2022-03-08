package com.example.mission3_basic.login.exception;

import com.example.mission3_basic.common.CustomException;
import com.example.mission3_basic.common.ErrorCode;

public class ExpiredTokenException extends CustomException {
    public ExpiredTokenException(){
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
