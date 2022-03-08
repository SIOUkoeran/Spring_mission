package com.example.mission3_basic.board.exception.exceptionHandler;

import com.example.mission3_basic.board.exception.InvalidWriterException;
import com.example.mission3_basic.board.exception.NotFoundBoardException;
import com.example.mission3_basic.common.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BoardExceptionHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(InvalidWriterException.class)
    protected ErrorResponse handleInvalidWriter(InvalidWriterException e){
        return new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getMessage());

    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NotFoundBoardException.class)
    protected ErrorResponse handleNotFoundBoard(NotFoundBoardException e){
        return new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getMessage());
    }
}
