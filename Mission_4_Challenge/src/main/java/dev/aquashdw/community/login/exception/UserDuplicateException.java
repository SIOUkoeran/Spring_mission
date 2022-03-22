package dev.aquashdw.community.login.exception;


import dev.aquashdw.community.dao.common.CustomException;
import dev.aquashdw.community.dao.common.ErrorCode;

public class UserDuplicateException extends CustomException {
    public UserDuplicateException(){
        super(ErrorCode.USER_DUPLICATE);
    }
}
