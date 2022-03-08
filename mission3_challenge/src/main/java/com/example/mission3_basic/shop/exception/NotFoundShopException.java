package com.example.mission3_basic.shop.exception;

import com.example.mission3_basic.Area.exception.NotFoundAreaException;
import com.example.mission3_basic.common.CustomException;
import com.example.mission3_basic.common.ErrorCode;

public class NotFoundShopException extends CustomException {
    public NotFoundShopException(){
        super(ErrorCode.NOT_FOUND_SHOP);
    }
}
