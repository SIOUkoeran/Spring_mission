package dev.aquashdw.community.exception;


import dev.aquashdw.community.dto.common.CustomException;
import dev.aquashdw.community.dto.common.ErrorCode;

public class UserDuplicateException extends CustomException {
    public UserDuplicateException(){
        super(ErrorCode.USER_DUPLICATE);
    }
}
