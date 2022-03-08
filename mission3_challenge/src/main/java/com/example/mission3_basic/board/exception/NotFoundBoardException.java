package com.example.mission3_basic.board.exception;

import com.example.mission3_basic.common.CustomException;
import com.example.mission3_basic.common.ErrorCode;

public class NotFoundBoardException extends CustomException {
    public NotFoundBoardException(){
        super(ErrorCode.NOT_FOUND_BOARD);
    }
}
