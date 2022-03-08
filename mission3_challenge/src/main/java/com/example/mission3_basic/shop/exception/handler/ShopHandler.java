package com.example.mission3_basic.shop.exception.handler;


import com.example.mission3_basic.common.response.ErrorResponse;
import com.example.mission3_basic.shop.exception.NotFoundShopException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShopHandler {

    @ExceptionHandler(NotFoundShopException.class)
    protected ErrorResponse handleNotFoundShopException(NotFoundShopException e){
        return new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getMessage());
    }
}
