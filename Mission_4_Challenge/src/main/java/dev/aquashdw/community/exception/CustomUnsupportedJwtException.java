package dev.aquashdw.community.exception;

import dev.aquashdw.community.dto.common.CustomException;
import dev.aquashdw.community.dto.common.ErrorCode;

public class CustomUnsupportedJwtException extends CustomException {
    public CustomUnsupportedJwtException(){
        super(ErrorCode.UNSUPPORTED_TOKEN);
    }
}
