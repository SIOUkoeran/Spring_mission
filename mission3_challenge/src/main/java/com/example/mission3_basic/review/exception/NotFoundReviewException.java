package com.example.mission3_basic.review.exception;

import com.example.mission3_basic.common.CustomException;
import com.example.mission3_basic.common.ErrorCode;
import com.example.mission3_basic.shop.exception.NotFoundShopException;

public class NotFoundReviewException extends CustomException {
    public NotFoundReviewException(){
        super(ErrorCode.NOT_FOUND_REVIEW);
    }
}
