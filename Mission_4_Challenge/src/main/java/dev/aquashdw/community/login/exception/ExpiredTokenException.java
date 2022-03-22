package dev.aquashdw.community.login.exception;

import dev.aquashdw.community.dao.common.CustomException;
import dev.aquashdw.community.dao.common.ErrorCode;

public class ExpiredTokenException extends CustomException {
    public ExpiredTokenException(){
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
