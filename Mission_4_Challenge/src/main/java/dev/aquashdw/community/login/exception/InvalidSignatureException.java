package dev.aquashdw.community.login.exception;


import dev.aquashdw.community.dao.common.CustomException;
import dev.aquashdw.community.dao.common.ErrorCode;

public class InvalidSignatureException extends CustomException {
    public InvalidSignatureException() {
        super(ErrorCode.INVALID_SIGNATURE);
    }
}
