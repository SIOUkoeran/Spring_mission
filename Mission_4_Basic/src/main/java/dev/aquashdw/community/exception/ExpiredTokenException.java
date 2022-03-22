package dev.aquashdw.community.exception;

import dev.aquashdw.community.dto.common.CustomException;
import dev.aquashdw.community.dto.common.ErrorCode;

public class ExpiredTokenException extends CustomException {
    public ExpiredTokenException(){
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
