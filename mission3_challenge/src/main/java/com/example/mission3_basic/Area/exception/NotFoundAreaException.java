package com.example.mission3_basic.Area.exception;

import com.example.mission3_basic.common.CustomException;
import com.example.mission3_basic.common.ErrorCode;

public class NotFoundAreaException extends CustomException {
    public NotFoundAreaException(){
        super(ErrorCode.NOT_FOUND_AREA);
    }
}
