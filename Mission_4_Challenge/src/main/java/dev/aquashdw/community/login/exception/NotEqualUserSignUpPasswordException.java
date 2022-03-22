package dev.aquashdw.community.login.exception;

import dev.aquashdw.community.dao.common.CustomException;
import dev.aquashdw.community.dao.common.ErrorCode;

public class NotEqualUserSignUpPasswordException extends CustomException {
    public NotEqualUserSignUpPasswordException(){
        super(ErrorCode.NOT_EQUAL_USER_PASSWORD);
    }
}
