package dev.aquashdw.community.login.exception;

import dev.aquashdw.community.dao.common.CustomException;
import dev.aquashdw.community.dao.common.ErrorCode;

public class InvalidTokenException extends CustomException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
