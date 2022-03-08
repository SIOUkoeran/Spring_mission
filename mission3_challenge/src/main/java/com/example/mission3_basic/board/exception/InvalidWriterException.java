package com.example.mission3_basic.board.exception;

import com.example.mission3_basic.common.CustomException;
import com.example.mission3_basic.common.ErrorCode;

public class InvalidWriterException extends CustomException {
    public InvalidWriterException(){
        super(ErrorCode.INVALID_WIRTER);
    }
}
