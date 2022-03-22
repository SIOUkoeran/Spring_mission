package dev.aquashdw.community.exception;

import dev.aquashdw.community.dto.common.CustomException;
import dev.aquashdw.community.dto.common.ErrorCode;

public class NotEqualUserSignUpPasswordException extends CustomException {
    public NotEqualUserSignUpPasswordException(){
        super(ErrorCode.NOT_EQUAL_USER_PASSWORD);
    }
}
