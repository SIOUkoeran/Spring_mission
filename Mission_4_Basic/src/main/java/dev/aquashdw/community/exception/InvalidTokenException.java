package dev.aquashdw.community.exception;

import dev.aquashdw.community.dto.common.CustomException;
import dev.aquashdw.community.dto.common.ErrorCode;

public class InvalidTokenException extends CustomException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
