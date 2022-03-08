package com.example.mission3_basic.review.exception.handler;


import com.example.mission3_basic.common.response.ErrorResponse;
import com.example.mission3_basic.review.exception.NotFoundReviewException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReviewExceptionHandler {


    @ExceptionHandler(NotFoundReviewException.class)
    protected ErrorResponse handleNotFoundReviewException(NotFoundReviewException e){
        return new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getMessage());
    }
}
