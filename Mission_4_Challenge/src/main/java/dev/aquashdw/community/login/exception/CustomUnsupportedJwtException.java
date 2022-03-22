package dev.aquashdw.community.login.exception;

import dev.aquashdw.community.dao.common.CustomException;
import dev.aquashdw.community.dao.common.ErrorCode;

public class CustomUnsupportedJwtException extends CustomException {
    public CustomUnsupportedJwtException(){
        super(ErrorCode.UNSUPPORTED_TOKEN);
    }
}
