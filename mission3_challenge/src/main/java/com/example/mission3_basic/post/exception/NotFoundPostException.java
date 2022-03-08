package com.example.mission3_basic.post.exception;

import com.example.mission3_basic.common.CustomException;
import com.example.mission3_basic.common.ErrorCode;

public class NotFoundPostException extends CustomException {
    public NotFoundPostException(){
        super(ErrorCode.NOT_FOUND_POST);
    }
}
