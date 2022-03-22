package dev.aquashdw.community.exception;


import dev.aquashdw.community.dto.common.CustomException;
import dev.aquashdw.community.dto.common.ErrorCode;

public class InvalidSignatureException extends CustomException {
    public InvalidSignatureException() {
        super(ErrorCode.INVALID_SIGNATURE);
    }
}
